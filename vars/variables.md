# Variables

def config = [
// Slack send share library
    // Mandatory
    channel: 'aws-chat-testing',
    slackToken: "slack-bot-99",           
    teamDomain: 'testing',        // Enter your workspace name
                    
// Slack with weebhooks
    webhook : "webhook-url",

    // Optional   [Common for both slack_token slack_curl]
    message: null,
    pass: "S",                    // Default value is S (success) for failure F (fail)
    title: null,                  // Default -> Updates
    footer: null,                 // Default --> jenkins
    pretext:  null,               // Default --> jenkins
    footerIcon: null,             // Default --> 'https://jenkins.io/images/logos/jenkins/256.png'

    
// Checkout shared library 
    // Mandatory
    gitUrl: "https://github.com/Vaibhavraj-nath-chauhan/sharedLibraryDemo.git",
    branch: "main",                                        // Default staging
    // Optional
    gitToken: "git-hub-new-vaibhavraj05",

// Secret Shared Library 
    // Mandatory
    secretToken:"yocharge-secret-manager",
    secretRegion:"ap-south-1",
    secretName:"yocharge-client-test-admin",

// Terrafrom Shared Library
    // Mandatory
    secretToken: "aws-new-cred-1",
    awsProfile: "share",
    awsRegion: "us-east-1",
    workDir: " ",
    // Optional
    // dockerImage: "dockerImage",             // Default -> msshahanshah/tools:terrform02
    action: "init",                           // Actions -> init, plan, apply, destroy
    extraParameter: null
                        
]