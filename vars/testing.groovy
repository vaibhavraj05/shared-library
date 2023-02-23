def call(String channel = 'aws-chat-testing', String token,String pass = "success", String footer = 'Jenkins', String pretext = "Jenkins" , String footer_icon = 'https://jenkins.io/images/logos/jenkins/256.png', String teamDomain = "testing") {
  def color= '#00FF00'
  def status = "Build Successfully"
  if(pass != 'success'){
    color= '#FF0000'
    status = "Build Failed"
  }

  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}}" //Fetching the build trigger
  slackSend (
      
  attachments: [
      [
      color: "${color}",
      pretext: "Notification From ${pretext}",
      title: 'Updates',
        text: "${status}\n${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n Build Output: (<${env.BUILD_URL}/console|Open>) \n Commit URL (<${env.url}|Open>)\n Commit Details ->\n ${env.commit_details}",
        footer: "${footer}",
       footer_icon: "${footer_icon}"
      ]
    ],
    channel: "${channel}",
    teamDomain:"${teamDomain}",
    tokenCredentialId: "${token}",
  )
}
