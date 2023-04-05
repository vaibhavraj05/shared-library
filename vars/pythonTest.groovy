#!/usr/bin/env groovy

def call(Map config){
    def data = sh (returnStdout: true, script: '''cat Dockerfile | grep FROM | head -n 1 | cut -d ":" -f 2| cut -d " " -f 1''').trim()
    try {
        def setup = sh (returnStdout: false, script:"docker run --rm -v ${workspace}:/app -w /app --name python --env-file=.env --network common python:${data} /bin/bash -c 'pip3 install -r requirements.txt && pytest -rp --junit-xml=test-report.xml >/dev/null 2>&1 && coverage xml'")
    }catch(e){
        echo ""
    }
    junit 'test-report.xml'
    sh "rm -f .env"

    if(currentBuild.resultIsBetterOrEqualTo('UNSTABLE') || config.pass == "U")
    {
        return "U"
    }
    return "S"
    
}
