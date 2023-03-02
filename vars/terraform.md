# Slack Send 

## Variables to pass 

#### Mandatory field
1. secretToken -> Secret token to access aws cli
2. awsProfile -> Profile we mnetion in our terrafrom script
3. awsRegion -> Region of aws
4. workDir -> Workdir in container
5. action -> Action we want to perfrom [init, plan,apply,destroy]

#### Optional field
6. terraformDockerImage -> "msshahanshah/tools:terrform02"
7. extraParameter -> Extra parameters we want to pass to command








#### Note:
    1. To access private channel we need to add the slack app in that channel 
    2. Mandatory field are required to pass else it will generate error
    3. To retrive the return value 
        def info = gitCheckout(config)
    4. To used to variable we can use 
        message = "${info.commitUrl} \n${info.commitDetails}"