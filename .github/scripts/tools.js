let path = require('path'),
    fs   = require('fs'),
    zlib = require("zlib"),
    {Buffer} = require("buffer"),
    fsPromises = fs.promises,
    { promisify } = require('util'),
    gzipAsync = promisify(zlib.gzip);


/**
 * Find all files recursively in specific folder with specific extension, e.g:
 * findFilesInDir('./project/src', ['.html']) ==> ['./project/src/a.html','./project/src/build/index.html']
 * @param  {String} startPath       Path relative to this file or other file which requires this files
 * @param  {String[]} filters       Extension names, e.g: ['.html']
 * @return {Promise<Array<String>>} Result files with path string in an array
 */
async function findFilesInDir(startPath, filters){
    let results = [];
    const dirStats = await fsPromises.stat(startPath);
    if (dirStats.isDirectory()) {
        const files = await fsPromises.readdir(startPath);
        for (let file of files) {
            let filename=path.join(startPath, file);
            const fileStats = await fsPromises.stat(filename);
            if (fileStats.isDirectory()) {
                for (let subFile of await findFilesInDir(filename, filters)) {
                    results.push(subFile);
                }
            } else if (filters.filter((filter) => filename.indexOf(filter)>=0).length > 0) {
                results.push(filename);
            }
        }
    }
    return results;
}

/**
 * GZIPs input data
 * @param  {String} input                Input data
 * @param  {zlib.ZlibOptions} options    Options for GZIP
 * @return {Promise<String>}             GZIP-ed data
 */
async function gzipData(input, options){
    return gzipAsync(input, options);
    // return new Promise( (resolve, reject) => {
    //     zlib.gzip(input, options, (error, result) => {
    //         if (!error) resolve(result);
    //         else reject(Error(error));
    //     });
    // });
}

/**
 * GZIPs content of the file
 * @param {String} path          Path to file
 * @param  {zlib.ZlibOptions} options    Options for GZIP
 * @returns {Promise<String>}    GZIP-ed data
 */
async function gzipFile(path, options) {
    return await gzipData(await fsPromises.readFile(path, {encoding: "utf8"}), options)
    // return new Promise(((resolve, reject) => {
    //     fs.readFile(path, (error, content) => {
    //         if (!error) {
    //             gzipData(content, {})
    //                 .then((zipped) => { resolve(zipped); })
    //                 .catch((failed) => { reject(failed); })
    //         } else {
    //             reject(error)
    //         }
    //     });
    // }));
}

/**
 * Convert input data to base64 format.
 * @param {String} input string data.
 * @returns {String} Base64 converted string.
 */
function strToBase64(input) {
    return Buffer.from(input).toString("base64")
}

/**
 * Searches for SARIF files in the directory, GZip them, convert to base64 and return as a map of path=>data.
 * @param {String} root The folder to scan.
 * @returns {Promise<Map<String, String>>} Map with path=>data of founded SARIF data.
 */
async function processSarifData(root) {
    const results= new Map();
    for (const sarifFile of await findFilesInDir(root, [".sarif", ".sarif.json"])) {
        results[sarifFile] = strToBase64(await gzipFile(sarifFile, {}));
    }
    return results;
}

module.exports = {
    findFilesInDir: findFilesInDir,
    gzipData: gzipData,
    gzipFile: gzipFile,
    strToBase64: strToBase64,
    processSarifData: processSarifData
};
