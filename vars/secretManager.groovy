#!/usr/bin/env groovy

def call(Map config){
  def secretToken = config.secretToken ?:error("No token Provided")              // SecretToken to access aws wtih cli
  def secretRegion = config.secretRegion ?: error("No region provided")          // Region in which our secrete is present
  def secretName = config.secretName ?: error("No secret name provided")         // Name fo the secret 
  withCredentials([string(credentialsId: secretToken, variable: 'token')]) {
    sh"""
        /bin/bash -c "export $token && aws secretsmanager get-secret-value --region ${secretRegion} --secret-id ${secretName} > data"
        cat data | jq .SecretString | jq --raw-output . | jq -r 'to_entries|map("\\(.key)=\\(.value|tostring)")|.[]' > .env 
    """
    sh 'rm -f data'
  }
}
