# Slack Send 

## Variables to pass 

#### Mandatory field
1. channel    -> Channel Id in which we want to send the message 
2. slackToken -> Token to access the workspace
3. teamDomain -> Workspace name

#### Optional field
4. message    -> Customize message according to needs
5. pass       -> Status of the job [S(success),F(Fail)]
6. title      -> Any specific title to send
7. footer     -> Name on footer
8. pretext    -> Pretext
9. footerIcon -> Logo on footer


#### Note:
    1. To access private channel we need to add the slack app in that channel 
    2. Mandatory field are required to pass else it will generate error