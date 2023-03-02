# Variables
 <br>
def config = [ <br>
// Slack send share library <br>
    // Mandatory <br>
    channel: 'aws-chat-testing', <br>
    slackToken: "slack-bot-99",            <br>
    teamDomain: 'testing',        // Enter your workspace name <br>
                     <br>
// Slack with weebhooks <br>
    webhook : "webhook-url", <br>
 <br>
    // Optional   [Common for both slack_token slack_curl] <br>
    message: null, <br>
    pass: "S",                    // Default value is S (success)  <br>for failure F (fail)
    title: null,                  // Default -> Updates <br>
    footer: null,                 // Default --> jenkins <br>
    pretext:  null,               // Default --> jenkins <br>
    footerIcon: null,             // Default --> 'https://jenkins. <br>io/images/logos/jenkins/256.png'
 <br>
     <br>
// Checkout shared library  <br>
    // Mandatory <br>
    gitUrl: "https://github.com/Vaibhavraj-nath-chauhan/ <br>sharedLibraryDemo.git",
    branch: "main",                                        //  <br>Default staging
    // Optional <br>
    gitToken: "git-hub-new-vaibhavraj05", <br>
 <br>
// Secret Shared Library  <br>
    // Mandatory <br>
    secretToken:"yocharge-secret-manager", <br>
    secretRegion:"ap-south-1", <br>
    secretName:"yocharge-client-test-admin", <br>
 <br>
// Terrafrom Shared Library <br>
    // Mandatory <br>
    secretToken: "aws-new-cred-1", <br>
    awsProfile: "share", <br>
    awsRegion: "us-east-1", <br>
    workDir: " ", <br>
    // Optional <br>
    // dockerImage: "dockerImage",             // Default ->  <br>msshahanshah/tools:terrform02
    action: "init",                           // Actions -> init,  <br>plan, apply, destroy
    extraParameter: null <br>
                         <br>
] <br>