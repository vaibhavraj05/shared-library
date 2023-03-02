#!/usr/bin/env groovy

def call(Map config) {
  
  def channel = config.channel ?: error('No channel provided')                       // Channel Id 
  def slackToken = config.slackToken ?: error('No token provided')                   // Token to access the workspace
  def teamDomain = config.teamDomain ?: error("No teamDomain provided")              // Workspace name
  def message = config.message ?: ''                                                 // Customize message according to needs
  def pass = config.pass ?: 'S'                                                      // Status of the job [S(success),F(Fail)]
  def title = config.title ?: 'Updates'                                              // Any specific title to send
  def footer = config.footer ?: 'Jenkins'                                            // Name on footer
  def pretext = config.pretext ?: 'Jenkins'                                          // Pretext
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png' // Logo on footer

  def color= '#00FF00'                                                               // Green for successful build
  def status = "Build Successfully"                                                  // Status of the build
  if(pass != 'S'){                                                     
    color= '#FF0000'                                                                 // Red for fail build
    status = "Build Failed"                                                          // Status fo the build
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}}" //Fetching the build trigger
  slackSend (    
    attachments: [
        [
          color: "${color}",
          pretext: "Notification From ${pretext}",
          title: "${title} -> ${status}",
          text: "${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n Build Output: (<${env.BUILD_URL}/console|Open>) \n${message}",
          footer: "${footer}",
          footer_icon: "${footerIcon}"
        ]
      ],
      channel: "${channel}",
      teamDomain:"${teamDomain}",
      tokenCredentialId: "${slackToken}",
  )
}
