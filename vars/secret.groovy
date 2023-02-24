#!/usr/bin/env groovy

def call(Map config){
  def secretToken = config.secretToken ?:error("No token Provided")
  def secretRegion = config.secretRegion ?: error("No region provided")
  def secretName = config.secretName ?: error("No secret name provided")
  withCredentials([string(credentialsId: secretToken, variable: 'accel')]) {
    sh"""
                /bin/bash -c "export $accel && aws secretsmanager get-secret-value --region ${secretRegion} --secret-id ${secretName} > data"
                cat data | jq .SecretString | jq --raw-output . | jq -r 'to_entries|map("\\(.key)=\\(.value|tostring)")|.[]' > .env 
            """
    sh 'rm -f data'
    sh "cat .env"
        }
}
