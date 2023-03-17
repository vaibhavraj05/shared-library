# Dependency Track

## Mandatory fields
1. language [specify that the code base is made on which language] 
2. apikey [specify the apikey for the dependency track.]
3.      if(autocreateproject == false){
            projectid [specify the project id which you made on the dependency dashboard]
        }
        else if(autocreateproject == true){
            projectname  [specify the project name which you want to specify your project.]
        }

## Default fields which you can overwrite
1. packagelocation (default: './') (format: './<folder>/)  [specify the location where your package.json or the requirements.txt file is present]
2. autocreateproject (default: false) [specify the project options like true/false]
3. frontendurl (default: 'https://dtrack.gkmit.co') [specify the frontend url of the dependency track dashboard]
4. backendurl (default: 'https://dtrackapi.gkmit.co') [specify the backend url of the dependency track dashboard]
5. failedtotalcritical (default: 100) [specify the failing constraint of the job if it is in critical stage]
6. failedtotalhigh (default: 100) [specify the failing constraint of the job if it is in high stage]
7. unstablenewcritical (default: 1) [specify the unstable contraint of the job if any new critical is encountered]
8. unstabletotalcritical (default: 1) [specify the unstable contraint of the job based on total criticals]
9. unstabletotalhigh (default: 1) [specify the unstable contraint of the job based on total highs]
10. nodeversion (default: 10.18.1) [specify the nodeversion on which you want to do the dependency check]
11. version (default: '01') [specify the version of the project]