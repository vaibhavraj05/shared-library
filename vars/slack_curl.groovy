#!/usr/bin/env groovy

def call(Map config) {
  def webhook = config.webhook ?: error('No webhook provided')           // Webhook url used to send message to channel
  def message = config.message ?: ''                                     // Custom message we cant to send          
  def pass = config.pass ?: 'S'                                          // Status of the job [S(success), F(fail)]
  def title = config.title ?: 'Updates'                                  // Custom title we want to send
  def footer = config.footer ?: 'Jenkins'                                // Custom footer we want to send 
  def pretext = config.pretext ?: 'Jenkins'                              // Custom pretext we want to send 
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png' // Custrom logo on footer

  
  
  def color= '#00FF00'                                                    // Green for successful build
  def status = "Build Successfully"                                       // Status of the build
  if(pass != 'S'){                                               
    color= '#FF0000'                                                      // Red for fail build
    status = "Build Failed"                                               // Status fo the build
  }
  
  withCredentials([string(credentialsId: "${webhook}", variable: 'webhook_url')]) {
    env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}" //Fetching the build trigger
    def objects = """'{
       "username": "vaibhavraj",
        "attachments":[
                  {
                    "color": "${color}",
                    "pretext": "Notification From ${pretext}",
                    "title": "${title} -> ${status}",
                    "text": "${env.BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n Build Output: (<${env.BUILD_URL}/console|Open>) \n${message}",
                    "footer": "${footer}",
                    "footer_icon": "${footerIcon}"
                  }
                ]
    }' "${webhook_url}"
    """
    sh "set +x;curl -X POST -H 'Content-type: application/json' --data ${objects}"
    
  }
  
}
