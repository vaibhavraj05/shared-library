def call(map config) {
  
  def channel = config.channel ?: 'aws-chat-testing'
  def token = config.token ?: error('No token provided')
  def message = config.message ?: error('No message provided')
  def pass = config.pass ?: 'success'
  def footer = config.footer ?: 'Jenkins'
  def pretext = config.pretext ?: 'Jenkins'
  def footer_icon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png'
  def teamDomain = config.teamDomain ?: 'testing'
  
//   String channel, String token, String message, String pass = "success", String footer = 'Jenkins', String pretext = "Jenkins" , String footer_icon = 'https://jenkins.io/images/logos/jenkins/256.png', String teamDomain = "testing"
  def color= '#00FF00'
  def status = "Build Successfully"
  if(pass != 'success'){
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
       footer_icon: "${footer_icon}"
      ]
    ],
    channel: "${channel}",
    teamDomain:"${teamDomain}",
    tokenCredentialId: "${token}",
  )
}
