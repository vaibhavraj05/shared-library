#!/usr/bin/env groovy

def call(String webhook, String pass = "success") {
  def color= '#FF0000'
  def status = "Build Failed"
  if(pass == 'success'){
    color= '#00FF00'
    status = "Build Successful"
  }
  env.BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription} / ${currentBuild.getBuildCauses()[0].userId}" //Fetching the build trigger
  def url = "https://google.com"
  def objects = """'{
     "username": "vaibhavraj",
      "attachments":[
                {
                  "pretext": "Notification From Jenkins",
                  "title": Updates,
                  "title_link": "https://jenkins.gkmit.co/view/accel/job/accel-staging/",
                  "text": ${status}\n${BUILD_TRIGGER_BY} \nJob Name: ${env.JOB_NAME} ${env.BUILD_NUMBER} \n Build Output: (<${env.BUILD_URL}/console|Open>) \n Commit URL (<${url}|Open>),
                  "footer": "testing",
                  "footer_icon": "https://media.licdn.com/dms/image/C560BAQGIOZDvliUyqA/company-logo_200_200/0/1637701111354?e=1684972800&v=beta&t=o4C4cxZu226JNl4Td4GPqOct6jbqi_GUnIuC0SlSEgc",
                  "color": "#006400"
                }
              ]
  }' ${webhook}
  """
  echo "${objects}"
  sh "curl -X POST -H 'Content-type: application/json' --data ${objects}"
}
