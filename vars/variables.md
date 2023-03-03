# Variables
 <br>
def config = [ <br>
// Slack send share library <br>
    // Mandatory <br>
&nbsp;&nbsp;&nbsp;&nbsp; channel: '', <br>
&nbsp;&nbsp;&nbsp;&nbsp; slackToken: '',            <br>
&nbsp;&nbsp;&nbsp;&nbsp; teamDomain: '', // Enter your workspace name <br>
                     <br>
// Slack with weebhooks <br>
&nbsp;&nbsp;&nbsp;&nbsp; webhook : '', <br>
 <br>
    // Optional   [Common for both slack_token slack_curl] <br>
    &nbsp;&nbsp;&nbsp;&nbsp; message: null, <br>
    &nbsp;&nbsp;&nbsp;&nbsp; pass: "S",                    // Default value is S (success)for failure F (fail) <br>
    &nbsp;&nbsp;&nbsp;&nbsp; title: null,                  // Default -> Build Successful <br>
    &nbsp;&nbsp;&nbsp;&nbsp; footer: null,                 // Default --> jenkins <br>
    &nbsp;&nbsp;&nbsp;&nbsp; pretext:  null,               // Default --> jenkins <br>
    &nbsp;&nbsp;&nbsp;&nbsp; footer_icon: null,             // Default --> 'https://jenkins.io/images/logos/jenkins/256.png' <br>
     <br>
// Checkout shared library  <br>
    // Mandatory <br>
    &nbsp;&nbsp;&nbsp;&nbsp; gitUrl: "", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; branch: "", <br>
    // Optional <br>
    &nbsp;&nbsp;&nbsp;&nbsp; gitToken: "", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; subModule: "false" , <br>
 <br>
// Secret Shared Library  <br>
    // Mandatory <br>
    &nbsp;&nbsp;&nbsp;&nbsp; secretToken:"", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; secretRegion:"", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; secretName:"", <br>
 <br>
// Terrafrom Shared Library <br>
    // Mandatory <br>
    &nbsp;&nbsp;&nbsp;&nbsp; secretToken: "", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; awsProfile: "", <br>
    &nbsp;&nbsp;&nbsp;&nbsp;awsRegion: "", <br>
    &nbsp;&nbsp;&nbsp;&nbsp; workDir: " ", <br>
    // Optional <br>
    // dockerImage: "dockerImage",             // Default -> msshahanshah/tools:terrform02 <br>
    &nbsp;&nbsp;&nbsp;&nbsp; action: "init",                           // Actions -> init, plan, apply, destroy <br>
    &nbsp;&nbsp;&nbsp;&nbsp; extraParameter: null ,<br>
                         <br>
// docker images tag       <br>
    &nbsp;&nbsp;&nbsp;&nbsp; DOCKER_IMAGE_NAME: '' <br>
] <br>
