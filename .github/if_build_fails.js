const tools = require("./scripts/tools");
module.exports = async ({ github, context }) => {
    let pull = null;
    if (context.issue.number) {
        const { data: result } = await github.rest.pulls.get({
            pull_number: context.issue.number,
            owner: context.repo.owner,
            repo: context.repo.repo,
        });
        pull = result;
    }
    if (pull && pull.number) {
        console.log(`Found pull request #${pull.number} from ${pull.actor} assigned to ${pull.assignee}`);
        if (pull.labels.findIndex((lbl) => lbl.name === "build_failed") < 0) {
            await github.rest.issues.addLabels({
                issue_number: pull.number,
                owner: context.repo.owner,
                repo: context.repo.repo,
                labels: ["build_failed"],
            });
            console.log(`Added 'build_failed' label`);
        }
        if (pull.labels.findIndex((lbl) => lbl.name === "build_passed") > -1) {
            await github.rest.issues.removeLabel({
                issue_number: pull.number,
                owner: context.repo.owner,
                repo: context.repo.repo,
                name: "build_passed",
            });
            console.log(`Removed 'build_passed' label`);
        }
        await github.rest.issues.createComment({
            issue_number: pull.number,
            owner: context.repo.owner,
            repo: context.repo.repo,
            body: `После коммита ${context.sha} проект невозможно собрать. Разрешение отозвано, требуются исправления.`,
        });
        console.log(`Added status comment`);

        let newTitle = pull.title.trimLeft();
        if (!newTitle.startsWith("WIP:")) {
            if (newTitle.startsWith("DONE:")) {
                newTitle = newTitle.trimLeft().substr(5);
            }
            newTitle = "WIP: " + newTitle.trimLeft();
            if (newTitle.trimLeft() !== pull.title.trimLeft()) {
                await github.rest.pulls.update({
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    pull_number: pull.number,
                    title: newTitle,
                });
                console.log(`Updated PR title from '${pull.title}' to '${newTitle}'`);
            }
        }

        for (let [path, data] of await tools.processSarifData(`${process.env.GITHUB_WORKSPACE}/`)) {
            console.log(`Found SARIF Data in file '${path}'..`);
            try {
                await github.rest.codeScanning.uploadSarif({
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    commit_sha: context.sha,
                    ref: context.ref,
                    sarif: data,
                });
                console.log(`Uploaded SARIF Data from file '${path}'.`);
            } catch (e) {
                console.log(`Uploading of SARIF Data is not enabled.`);
            }

        }


    }
    return null;
};
