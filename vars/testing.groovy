#!/usr/bin/env groovy

def call(String channel = 'aws-chat-testing', String token, String pass = "success") {
  
  if(pass == 'success'){
    def color: '#00FF00'
    def status = "Build Successful"
  }
  else{
    def color: '#FF0000'
    def status = "Build Failed"
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
  slackSend (
      
  attachments: [
      [
      color: color,
      pretext: 'Notification From Jenkins',
      title: 'Updates',
      text: "${status}\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n ",
      footer: 'Jenkins',
      footer_icon: 'https://jenkins.io/images/logos/jenkins/256.png'
      ]
    ],
    channel: "${channel}",
    teamDomain:'testing',
    tokenCredentialId: "${token}",
  )
}
