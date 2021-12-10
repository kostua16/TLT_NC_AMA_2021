const tools = require("./scripts/tools");
module.exports = async ({ github, context }) => {
    if (context.issue.number) {
        const { data: pull } = await github.rest.pulls.get({
            pull_number: context.issue.number,
            owner: context.repo.owner,
            repo: context.repo.repo,
        });
        // console.log(pull);
        if (pull && pull.number) {
            console.log(`Found pull request #${pull.number} from ${pull.actor} assigned to ${pull.assignee}`);
            if (pull.labels.findIndex((lbl) => lbl.name === "build_passed") < 0) {
                await github.rest.issues.addLabels({
                    issue_number: pull.number,
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    labels: ["build_passed"],
                });
                console.log(`Added 'build_passed' label`);
            }
            if (pull.labels.findIndex((lbl) => lbl.name === "build_failed") > -1) {
                await github.rest.issues.removeLabel({
                    issue_number: pull.number,
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    name: "build_failed",
                });
                console.log(`Removed 'build_failed' label`);
            }
            await github.rest.issues.createComment({
                issue_number: pull.number,
                owner: context.repo.owner,
                repo: context.repo.repo,
                body: `Проект успешно собрался для коммита ${context.sha}. Разрешение получено.`,
            });
            console.log(`Added status comment`);

            if (context.actor !==  context.repo.owner && context.repo.owner !== pull.assignee)  {
                await github.rest.pulls.requestReviewers({
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    pull_number: pull.number,
                    reviewers: [context.repo.owner]
                });
                console.log(`Requested review`);
            }
            let newTitle = pull.title.trimLeft();

            if (!newTitle.startsWith("DONE:")) {
                if (newTitle.startsWith("WIP:")) {
                    newTitle = newTitle.trimLeft().substr(4);
                }
                newTitle = "DONE: " + newTitle.trimLeft();
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
                await github.rest.codeScanning.uploadSarif({
                    owner: context.repo.owner,
                    repo: context.repo.repo,
                    commit_sha: context.sha,
                    ref: context.ref,
                    sarif: data,
                });
                console.log(`Uploaded SARIF Data from file '${path}''`);
            }
        }
    }

    return null;
};
