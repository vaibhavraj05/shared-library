#!/usr/bin/env groovy

def call(String name = 'human') {
      slackSend (
      attachments: [
          [
              color: '#FFFF00',
              pretext: 'Notification From Jenkins',
              title: 'Updates',
              text: "Build Started\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n ",
              footer: 'Jenkins',
              footer_icon: 'https://jenkins.io/images/logos/jenkins/256.png'
          ]
        ],
        channel: 'aws-chat-testing',
        teamDomain:'testing',
        tokenCredentialId: 'slack-bot-99',
      )
}