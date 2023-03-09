#!/usr/bin/env groovy

def call(Map config){
    def gmailCred = config.gmailCred ?: error("No mail authentication provided")
    def groupMail = config.groupMail ?: error("No mail group Provided")
    def mailFrom = config.mailFrom ?: "vaibhavraj@gkmit.co"
    def message =  config.message ?: " "
    def subject = config.subject ?: "Project Detatils"
    withCredentials([string(credentialsId: 'test-email-temp', variable: 'gmailTemplate')]) {
    echo "I am here"
    sh "set -x;curl --url 'smtps://smtp.gmail.com:465' --ssl-reqd --mail-from ${mailFrom} --mail-rcpt ${groupMail} -F text=\"${message}\" --user ${gmailTemplate} --header \"Subject: ${subject}\""
    }


}

// stage("Mail"){
    //     mail bcc: '', body: 'Group Working fine', cc: '', from: 'vaibhavrajbanna@gmai.com', replyTo: '', subject: 'Testing Group', to: 'testing0501@googlegroups.com'
    // }