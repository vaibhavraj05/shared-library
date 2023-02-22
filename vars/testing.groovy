def call(String channel = 'aws-chat-testing', String token,String pass = "success", String footer = 'Jenkins', String pretext = "Notification FRom Jenkins", String text = "Build Successfull" , String footer_icon = 'https://jenkins.io/images/logos/jenkins/256.png') {
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
