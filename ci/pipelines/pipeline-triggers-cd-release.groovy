pipeline {
    agent { label 'node' }
    stages {
        stage('Build') { 
            steps {
                echo 'This is the build stage'
            }
        }
        stage('Test') { 
            steps {
                echo 'This is the test stage'
            }
        }
        stage('Handover to CloudBees CD') { 
            steps {
                echo 'This is the Handover to CloudBees CD stage'
                cloudBeesFlowTriggerRelease configuration: 'CloudBees Guru CD', parameters: '{"release":{"releaseName":"Triggered by Jenkins","stages":[{"stageName":"Post Build","stageValue":false}],"pipelineName":"pipeline_Triggered by Jenkins","parameters":[]}}', projectName: 'Examples Library', releaseName: 'Triggered by Jenkins', startingStage: 'Post Build'
            }
        }
    }
}
