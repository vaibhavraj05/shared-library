#!/usr/bin/env groovy

def call(String channel = 'aws-chat-testing', String token, String pass = "success") {
  def color= '#FF0000'
   def status = "Build Failed"
  if(pass == 'success'){
    color= '#00FF00'
    status = "Build Successful"
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
  slackSend (
      
  attachments: [
      [
        color: "${color}",
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
