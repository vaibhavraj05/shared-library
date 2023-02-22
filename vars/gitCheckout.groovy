#!/usr/bin/env groovy

def call(String gitUrl, String branch = 'staging', String token = " ") {
   checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], userRemoteConfigs: [[url: "${gitUrl}", credentialsId: "${token}"]]])
   
}
