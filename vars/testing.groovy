#!/usr/bin/env groovy

def call(String channel = 'aws-chat-testing', String token, String pass = "success") {
  if(pass == 'success'){
      slackSend (
      attachments: [
          [
              color: '#00FF00',
              pretext: 'Notification From Jenkins',
              title: 'Updates',
              text: "Build Started\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n ",
              footer: 'Jenkins',
              footer_icon: 'https://jenkins.io/images/logos/jenkins/256.png'
          ]
        ],
        channel: "${channel}",
        teamDomain:'testing',
        tokenCredentialId: "${token}",
      )
    }
  else{
      slackSend (
      attachments: [
          [
              color: '#FF0000',
              pretext: 'Notification From Jenkins',
              title: 'Updates',
              text: "Build Started\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n ",
              footer: 'Jenkins',
              footer_icon: 'https://jenkins.io/images/logos/jenkins/256.png'
          ]
        ],
        channel: "${channel}",
        teamDomain:'testing',
        tokenCredentialId: "${token}",
      )

      }
}
