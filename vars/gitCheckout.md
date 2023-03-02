# Git Checkout

## Variables to pass

#### Mandatory field
1. gitUrl  -> Git repo url 
2. branch  -> Branch we need to pass



#### Optional field
3. gitToken  -> Token to access private repo



#### Note
 1. To retrive the return value <br>
        def info = gitCheckout(config)

 2. To used to variable we can use  <br>
       message = "${info.commitUrl} ${info.commitDetails}"
 3. Mandatory field are required to pass else it will generate error
