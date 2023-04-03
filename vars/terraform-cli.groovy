#!/usr/bin/env groovy

def call(Map config){

    def secretToken = config.secretToken ?:error("No token Provided")         // Secret token to access aws cli
    def awsProfile = config.awsProfile ?: error("No prfile provided")         // Profile we mnetion in our terrafrom script
    def awsRegion = config.awsRegion ?: error("No region provided")           // Region of aws
    def workDir = config.workDir ?: error("No working Directory provided")    // workdir in container
    def terraformDockerImage = "msshahanshah/tools:terrform02"                // Docker image with terrafrom configuration
    def action = config.action ?: error("No action provided")                 // Action we want to perfrom [init, plan,apply,destroy]

    withCredentials([string(credentialsId: "${secretToken}", variable: "setup")]) {
                def AWS_CREDENTIALS = sh (
                    script: """
                        set +x; echo ${setup} | awk '{print \"-e\", \$1, \"-e\", \$2}'
                    """,
                    returnStdout: true
                ).trim()
         sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w /code/${workDir} ${terraformDockerImage} /bin/bash -c '${action}'"           
        }
}
