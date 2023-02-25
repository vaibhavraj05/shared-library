#!/usr/bin/env groovy

def call(Map config) {
   def gitUrl = config.gitUrl ?: error("No url passed")
   def branch = config.branch ?: "staging"
   def gitToken = config.gitToken ?: ""
   
   
   
   checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], userRemoteConfigs: [[url: "${gitUrl}", credentialsId: "${gitToken}"]]])
   def gitBaseUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url | sed "s/.git$/""/"').trim()  //Grep the git url
   def gitCommitLink = sh(returnStdout: true, script: 'git log -n 1 --pretty=format:"%H"').trim()                // Grep the recent commit
   def commitUrl= "${gitBaseUrl}/commit/${gitCommitLink}"                                                                 // Mergning bot the details
//    env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
   def commitDetails = sh (returnStdout: true, script: '''set +e && git log --format="short" -1 | tail -n +2 ''')       
   return [commitUrl: commitUrl,commitDetails: commitDetails]
   
}
