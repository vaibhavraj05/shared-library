#!/usr/bin/env groovy

def call(Map config) {
  def webhook = config.webhook ?: error('No webhook provided')           // Webhook url used to send message to channel
  def message = config.message ?: ''                                     // Custom message we cant to send          
  def pass = config.pass ?: 'S'                                          // Status of the job [S(success), F(fail)]
  def title = config.title ?: 'Build Successful'                                  // Custom title we want to send
  def build_num = config.BUILD_NUM ?: env.BUILD_NUMBER
  def job_name = config.job_name ?: env.JOB_NAME
  def footer = config.footer ?: 'Jenkins'                                // Custom footer we want to send 
  def pretext = config.pretext ?: 'Jenkins'                              // Custom pretext we want to send 
  def footerIcon = config.footer_icon ?: 'https://jenkins.io/images/logos/jenkins/256.png' // Custrom logo on footer

  
  
  def color= '#006400'                                                    // Green for successful build
  
  if(pass == 'F'){                                               
    color= '#FF0000'                                                      // Red for fail build
    title = "Build Failed"                                               // Status fo the build
  }else if(pass == 'U'){
    color = '#b0af3e'
    title = "Build Successfull (UNSTABLE)"
  }
  
  withCredentials([string(credentialsId: "${webhook}", variable: 'webhook_url')]) {
    env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}" //Fetching the build trigger
    def objects = """'{
        "attachments":[
                  {
                    "color": "${color}",
                    "pretext": "Notification From ${pretext}",
                    "title": "${title}",
                    "text": "${env.BUILD_TRIGGER_BY} \nJob Name: ${job_name} \nBuild Number: ${build_num} \nBuild Output: (<${env.BUILD_URL}console|Open>) \n${message}",
                    "footer": "${footer}",
                    "footer_icon": "${footerIcon}"
                  }
                ]
    }' "${webhook_url}"
    """
    sh "set +x;curl -X POST -H 'Content-type: application/json' --data ${objects}"
    
  }
  
}
