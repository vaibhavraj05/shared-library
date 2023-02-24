#!/usr/bin/env groovy

def call(Map config){
  def secretToken = secret.secretToken ?:error("No token Provided")
  def secretRegion = secret.secretRegion ?: error("No region provided")
  def secretName = secret.secretName ?: error("No secret name provided")
  withCredentials([string(credentialsId: "${secretToken}", variable: 'accel')]) {
    sh"""
                /bin/bash -c "export $accel && aws secretsmanager get-secret-value --region ${secretRegion} --secret-id ${secretName} > data"
                cat data | jq .SecretString | jq --raw-output . | jq -r 'to_entries|map("\\(.key)=\\(.value|tostring)")|.[]' > .env 
            """
    sh 'rm -f data'
    sh "cat .env"
        }
}
