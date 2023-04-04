#!/usr/bin/env groovy

def call(Map config){
    def extraCommand = config.extraCommand ?: "echo"
    def data = sh (returnStdout: true, script: '''cat Dockerfile | grep FROM | head -n 1 | cut -d ":" -f 2| cut -d " " -f 1''').trim()
    sh "docker run --rm -v ${workspace}:/app -w /app node:${data} ${extraCommand} && npm install"
    def result = sh (returnStatus: true, script: "docker run --rm -v ${workspace}:/app -w /app node:${data} /bin/bash -c 'CI=true npm run test:report'")
    junit 'test-report.xml'
    if(currentBuild.resultIsBetterOrEqualTo('UNSTABLE') || config.pass == "U")
    {
        return "U"
    }
    return "S"
    
}
