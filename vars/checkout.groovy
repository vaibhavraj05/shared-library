#!/usr/bin/env groovy

def call(String gitUrl, String branch = 'Staging', String token = '') {
  echo "Hello Wrolds"
  checkout([
            $class: 'GitSCM', 
            branches: [[name: "*/${branch}"]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [], 
            submoduleCfg: [], 
            userRemoteConfigs: [[
              credentialsId: "${token}", 
              url: "${gitUrl}"
                ]]
                ]
            )
   def gitUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url | sed "s/.git$/""/"').trim()  //Grep the git url
   def gitCommitLink = sh(returnStdout: true, script: 'git log -n 1 --pretty=format:"%H"').trim()                // Grep the recent commit
   env.url= "${gitUrl}/commit/${gitCommitLink}"                                                                 // Mergning bot the details
   env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
   env.commit_details = sh (returnStdout: true, script: '''set +e && git log --format="short" -1 | tail -n +2 ''')       
}
