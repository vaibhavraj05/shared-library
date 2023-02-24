#!/usr/bin/env groovy

def call(Map config){
  withCredentials([string(credentialsId: 'yocharge-secret-manager', variable: 'accel')]) {
            sh'''
                /bin/bash -c 'export $accel && aws secretsmanager get-secret-value --region us-east-1 --secret-id yocharge-test-admin > data'
                cat data | jq .SecretString | jq --raw-output . | jq -r 'to_entries|map("\\(.key)=\\(.value|tostring)")|.[]' > .env 
            '''
            sh 'rm -f data'
            sh "cat .env"
        }
}
