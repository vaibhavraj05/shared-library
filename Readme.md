# Usage

### Configuraton
    1. Adding the github shared library repo to jenkins master
      1. Dashboard -> Manage Jenkins -> Configure System -> Global Pipeline Libraries
      2. Configure the git repo their

### Setup the shared library in jenkins pipeline 
    1. In top of the script add 
        @Library("name-you-configured") _ 
    2. Configuring the variables
       1. Copy the variables from variables.md and paste in next line of @Library() _
<br>

### Basic Structure
    @Library("git-setup") _
    def config = [
        name: "GKMIT",
        team: "devops"
        ]
    node(""){} / pipeline{}

### Accessing the shared library function in jenkins pipeline
    1. In Scripted pipeline 
        node("master"){
            stage("Testing"){
                function_name(config)   // Sending map as variable
            }
        }
    2. In declerative pipeline
        pipeline{
            agent{
                label "master"
            }
            stages{
                stage("Testing"){
                    steps{
                        function_name(config) // Sending map as variable
                    }
                }
            }
        }

### Updating the Map [array]
    1. In Scripted pipeline 
        node("master"){
            stage("Testing"){
                config.name = "Learner"
                function_name(config)   // Sending map as variable
            }
        }
    2. In declerative pipeline
        pipeline{
            agent{
                label "master"
            }
            stages{
                stage("Testing"){
                    steps{
                        script{
                            config.name = "Learner"
                            function_name(config) // Sending map as variable
                        }
                    }
                }
            }
        }


### Accessing our shared library function 
    ## Note Configure the variables first

    1. Slack_token
        slack_token(config)
    2. Slack_curl
        slack_curl(config)
    3. Secret Manager
        secretManager(config)
    4. Terraform
        terraform(config)
    5. Git Checkout
        def info = gitCheckout(config)

### Function who returning some values
    1. gitCheckout.groovy
    
   


