def call(Map config){
    // def package_file=sh(returnStdout: true, script:"find . -name package.json")
    def language = config.language ?: error("Please enter the language.")
    def packagelocation = config.packlocation ?: './'
    def autocreateproject = config.autocreateproject ?: false
    def apikey = config.apikey ?: error("Please enter the api_key")
    def frontendurl = config.frontendurl ?: 'https://dtrack.gkmit.co'
    def backendurl = config.backendurl ?: 'https://dtrackapi.gkmit.co'
    def failedtotalcritical = config.failedtotalcritical ?: 100
    def failedtotalhigh = config.failedtotalhigh ?: 100
    def unstablenewcritical = config.unstablenewcritical ?: 1
    def unstabletotalcritical = config.unstabletotalcritical ?: 1
    def unstabletotalhigh = config.unstabletotalhigh ?: 1
    def nodeversion = config.nodeversion ?: 14 //sh (returnStdout: true, script: '''cat Dockerfile | grep FROM | head -n 1 | cut -d ":" -f 2| cut -d " " -f 1''').trim()
    def unstabletotallow = config.unstabletotallow ?: 1
    def unstabletotalmedium = config.unstabletotalmedium ?: 1
    def extraCommand = config.extraCommand ?: 'echo'
    
    if("${language}" == 'node'){
        sh "docker run --rm -v ${workspace}:/src -w /src node:${nodeversion} ${extraCommand} && npm --prefix ${packagelocation} install"
        sh "docker run --rm -v ${workspace}:/src cyclonedx/cyclonedx-node /src/${packagelocation}"
        sh "rm -rf ${packagelocation}node_modules"
    }
    else if("${language}" == 'python'){
        sh "docker run --rm -v ${workspace}:/src -w /src cyclonedx/cyclonedx-python -r -i ${packagelocation}requirements.txt --format xml -o bom.xml"
    }
    else if("${language}" == 'angular'){
        sh "docker run --rm -v ${workspace}:/src -w /src node:${nodeversion} /bin/bash -c 'npm install -g @angular/cli && npm --prefix ${packagelocation} install'"
        sh "docker run --rm -v ${workspace}:/src cyclonedx/cyclonedx-node /src/${packagelocation}"
        sh "rm -rf ${packagelocation}node_modules"
    }
    
    withCredentials([string(credentialsId: "${apikey}", variable: 'API_KEY')]) 
    {
    if(autocreateproject == false){
        def projectid = config.projectid ?: error("Please enter the projectid.")

        dependencyTrackPublisher artifact: './bom.xml', 
        autoCreateProjects: "${autocreateproject}",
        dependencyTrackApiKey: "${API_KEY}", 
        dependencyTrackFrontendUrl: "${frontendurl}", 
        dependencyTrackUrl: "${backendurl}", 
        failedTotalCritical: "${failedtotalcritical}", 
        failedTotalHigh: "${failedtotalhigh}",
        projectId: "${projectid}",
        synchronous: true, 
        unstableNewCritical: "${unstablenewcritical}", 
        unstableTotalCritical: "${unstabletotalcritical}", 
        unstableTotalHigh: "${unstabletotalhigh}",
        unstableTotalLow: "${unstabletotallow}",
        unstableTotalMedium: "${unstabletotalmedium}"
    } 
    else if(autocreateproject == true){
        def projectname = config.projectname ?: error("Please specify the project name.")
        def version = config.version ?: '01'

        dependencyTrackPublisher artifact: './bom.xml', 
        autoCreateProjects: "${autocreateproject}",
        dependencyTrackApiKey: "${API_KEY}", 
        dependencyTrackFrontendUrl: "${frontendurl}", 
        dependencyTrackUrl: "${backendurl}", 
        failedTotalCritical: "${failedtotalcritical}", 
        failedTotalHigh: "${failedtotalhigh}",
        projectName: "${projectname}", 
        projectVersion: "${version}",
        synchronous: true, 
        unstableNewCritical: "${unstablenewcritical}", 
        unstableTotalCritical: "${unstabletotalcritical}", 
        unstableTotalHigh: "${unstabletotalhigh}",
        unstableTotalLow: "${unstabletotallow}",
        unstableTotalMedium: "${unstabletotalmedium}"
    }
    }
    if(currentBuild.result == 'UNSTABLE')
    {
        return "U"
    }
    return "S"
    
}

