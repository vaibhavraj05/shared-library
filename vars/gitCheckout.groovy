#!/usr/bin/env groovy

def call(Map config) {
   def gitUrl = config.gitUrl ?: error("No url passed")            // Git repo url 
   def branch = config.branch ?: error("No branch passed")         // Branch we need to pass
   def gitToken = config.gitToken ?: ""                            // Token to access private repo
   def subModule = config.subModule ?: false                       // If you want to apply submodule or not 
   
   
   cleanWs()
   
   checkout(
      [$class: 'GitSCM', 
      branches: [[name: "*/${branch}"]], 
      userRemoteConfigs: 
         [
            [
               url: "${gitUrl}", 
               credentialsId: "${gitToken}"
            ]
         ],
       doGenerateSubmoduleConfigurations: "${subModule}", 
        extensions: 
        [
           [
              $class: 'SubmoduleOption', 
              parentCredentials: true
           ]
        ]
      ]
   )

   // Below code will fetch the commit detials and the commit url 
   def gitBaseUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url | sed "s/.git$/""/"').trim()  //Grep the git url
   def gitCommitLink = sh(returnStdout: true, script: 'git log -n 1 --pretty=format:"%H"').trim()                // Grep the recent commit
   def commitUrl= "${gitBaseUrl}/commit/${gitCommitLink}"                                                                 // Mergning bot the details
   def commitDetails = sh (returnStdout: true, script: '''set +e && git log --format="short" -1 | tail -n +2 ''')       
   return [commitUrl: commitUrl,commitDetails: commitDetails]
   
}

// To retrive the return value 
//     def info = gitCheckout(config)

// To used to variable we can use 
//     message = "${info.commitUrl} \n${info.commitDetails}"
