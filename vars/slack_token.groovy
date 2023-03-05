#!/usr/bin/env groovy

def call(Map config) {
  
  def channel = config.channel ?: error('No channel provided')                       // Channel Id 
  def slackToken = config.slackToken ?: error('No token provided')                   // Token to access the workspace
  def teamDomain = config.teamDomain ?: error("No teamDomain provided")              // Workspace name
  def message = config.message ?: ''                                                 // Customize message according to needs
  def pass = config.pass ?: 'S'                                                      // Status of the job [S(success),F(Fail)]
  def title = config.title ?: 'Build Successful'                                              // Any specific title to send
  def build_num = config.BUILD_NUM ?: env.BUILD_NUMBER
  def job_name = config.job_name ?: env.JOB_NAME
  def footer = config.footer ?: 'Jenkins'                                            // Name on footer
  def pretext = config.pretext ?: 'Jenkins'                                          // Pretext
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png' // Logo on footer

  def color= '#006400'                                                               // Green for successful build
  if(pass != 'S'){                                                     
    color= '#FF0000'                                                                 // Red for fail build
    title = "Build Failed"
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}" //Fetching the build trigger
  slackSend (    
    attachments: [
        [
          color: "${color}",
          pretext: "Notification From ${pretext}",
          title: "${title}",
          text: "${env.BUILD_TRIGGER_BY}\nJob Name: ${job_name}\nBuild Number: ${build_num}\nBuild Output: (<${env.BUILD_URL}console|Open>)\n${message}",
          footer: "${footer}",
          footer_icon: "${footerIcon}",
        ]
      ],
      channel: "${channel}",
      username: "Devops Team", 
      teamDomain:"${teamDomain}",
      tokenCredentialId: "${slackToken}",
  )
}
