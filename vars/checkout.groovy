#!/usr/bin/env groovy

// def call(String gitUrl, String branch = 'staging') {
//   echo "Hello Wrolds"
  
//   checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], userRemoteConfigs: [[url: "${gitUrl}"]]])
    
//    def gitBaseUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url | sed "s/.git$/""/"').trim()  //Grep the git url
//    def gitCommitLink = sh(returnStdout: true, script: 'git log -n 1 --pretty=format:"%H"').trim()                // Grep the recent commit
//    env.url= "${gitBaseUrl}/commit/${gitCommitLink}"                                                                 // Mergning bot the details
//    env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
//    env.commit_details = sh (returnStdout: true, script: '''set +e && git log --format="short" -1 | tail -n +2 ''')       
// }
//             credentialsId: "${token}", 


def call(String repoUrl, String branch = 'master', String credentialId = '') {
    def scmVars = [
        $class: 'GitSCM',
        userRemoteConfigs: [[
            credentialsId: credentialId,
            url: repoUrl
        ]],
        branches: [[name: "*/${branch}"]],
        extensions: [
            [$class: 'CleanCheckout'],
            [$class: 'CloneOption', noTags: true, shallow: true, depth: 1]
        ]
    ]

    checkout(scmVars)
}
