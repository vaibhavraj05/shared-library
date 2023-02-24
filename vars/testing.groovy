#!/usr/bin/env groovy

def call(Map config) {
  
  def channel = config.channel ?: error('No channel provided')
  def slackToken = config.slackToken ?: error('No token provided')
  def teamDomain = config.teamDomain ?: error("No teamDomain provided")
  def message = config.message ?: ''
  def pass = config.pass ?: 'S'
  def title = config.title ?: 'Updates'
  def footer = config.footer ?: 'Jenkins'
  def pretext = config.pretext ?: 'Jenkins'
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png'

  
//   String channel, String token, String message, String pass = "success", String footer = 'Jenkins', String pretext = "Jenkins" , String footer_icon = 'https://jenkins.io/images/logos/jenkins/256.png', String teamDomain = "testing"
  def color= '#00FF00'
  def status = "Build Successfully"
  if(pass != 'S'){
    color= '#FF0000'
    status = "Build Failed"
  }
  echo "${message}"

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}}" //Fetching the build trigger
  slackSend (
      
  attachments: [
      [
      color: "${color}",
      pretext: "Notification From ${pretext}",
      title: 'Updates',
        text: "${status}\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n Build Output: (<${env.BUILD_URL}/console|Open>) \n${message}",
        footer: "${footer}",
       footer_icon: "${footerIcon}"
      ]
    ],
    channel: "${channel}",
    teamDomain:"${teamDomain}",
    tokenCredentialId: "${slackToken}",
  )
}
