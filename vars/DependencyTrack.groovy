def call(Map config){
    def package_file=sh '''find . -name package.json'''
    def packagelocation = config.packlocation ?: './backend'
    def autocreateproject = config.autocreateproject ?: false
    def apikey = config.api_key ?: error("Please enter the api_key")
    def frontendurl = config.frontendurl ?: 'https://dtrack.gkmit.co'
    def backendurl = config.backendurl ?: 'https://dtrackapi.gkmit.co'
    def failedtotalcritical = config.failedtotalcritical ?: 100
    def failedtotalhigh = config.failedtotalhigh ?: 100
    def unstablenewcritical = config.unstablenewcritical ?: 1
    def unstabletotalcritical = config.unstabletotalcritical ?: 1
    def unstabletotalhigh = config.unstabletotalhigh ?: 1
    def nodeversion = config.nodeversion ?: '10.18.1'
    echo "${nodeversion}"
   
    if("${package_file}" != ''){
        def pwd = sh "$(pwd)"
        sh "docker run --rm -v ${pwd}:/src -w /src node:${nodeversion} npm --prefix ${packagelocation} install"
        sh 'docker run --rm -v $(pwd):/src cyclonedx/cyclonedx-node /src/"${packagelocation}"'
    }
    else {
        sh 'docker run --rm -v $(pwd):/src -w /src cyclonedx/cyclonedx-python -r -i "${packagelocation}"/requirements.txt --format xml -o bom.xml'
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
        unstableTotalHigh: "${unstabletotalhigh}"
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
        unstableTotalHigh: "${unstabletotalhigh}"
    }
    }

}

