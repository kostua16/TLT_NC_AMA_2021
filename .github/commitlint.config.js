module.exports = {
    extends: ['@commitlint/config-conventional'],
    rules: {
        'subject-case': [
            1,
            'always',
            ['sentence-case', 'start-case', 'lower-case'],
        ],
        'body-leading-blank': [0, 'always'],
        'footer-max-line-length': [2, 'always', 120],
        'header-max-length': [2, 'always', 120],
        'type-enum': [
            2,
            'always',
            [
                'build',
                'chore',
                'ci',
                'docs',
                'feat',
                'feature',
                'fix',
                'perf',
                'refactor',
                'revert',
                'style',
                'test',
            ],
        ],
    },
    /*
   * Functions that return true if commitlint should ignore the given message.
   */
    ignores: [
        (commit) => commit === '',
        (commit) => commit.includes("CR_IGNORE"),
        (commit) => commit.toLowerCase().includes("bump"),
        (commit) => commit.toLowerCase().includes("merge "),
        (commit) => commit.toLowerCase().includes("workflows "),
    ],
    /*
     * Whether commitlint uses the default ignore rules.
     */
    defaultIgnores: true,
}
