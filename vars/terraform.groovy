#!/usr/bin/env groovy

def call(Map config){

    def secretToken = config.secretToken ?:error("No token Provided")         // Secret token to access aws cli
    def awsProfile = config.awsProfile ?: error("No prfile provided")         // Profile we mnetion in our terrafrom script
    def awsRegion = config.awsRegion ?: error("No region provided")           // Region of aws
    def workDir = config.workDir ?: error("No working Directory provided")    // workdir in container
    def terraformDockerImage = "msshahanshah/tools:terrform02"                // Docker image with terrafrom configuration
    def action = config.action ?: error("No action provided")                 // Action we want to perfrom [init, plan,apply,destroy]
    def extraParameter = config.extraParameter ?: ""                          // Extra parameters we want to pass to command


    withCredentials([string(credentialsId: "${secretToken}", variable: "setup")]) {
                def AWS_CREDENTIALS = sh (
                    script: """
                        set +x; echo ${setup} | awk '{print \"-e\", \$1, \"-e\", \$2}'
                    """,
                    returnStdout: true
                ).trim()
         sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w /code/${workDir} ${terraformDockerImage} terraform ${action} ${extraParameter}"   
         //sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=${awsProfile} -e AWS_REGION=${awsRegion} -v ${workspace}/:/code -w ${workDir} ${terraformDockerImage} terraform plan -var image_number=${imgTag}"
         //sh "set +x; docker run --rm ${AWS_CREDENTIALS} -e AWS_PROFILE=accel -e AWS_REGION=us-east-1 -v ${workspace}/:/code -w /code/accel-ec2/services-prod/website-comparison msshahanshah/tools:terrform01 terraform apply -var image_number=${img_tag}"
        
        }
}
