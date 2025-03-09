library identifier: 'my-nn-jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/devbird007/my-nn-jenkins-shared-library.git',
    credentialsId: 'github-credentials'
    ]
)

def gv

pipeline {
    agent any
    tools {
    // It comes in the format:
    //  - <tool> '<tool_instance_name_as_created_in_the_jenkins_ui'
    //  Note that 'maven-3.9.9' isn't the version. I gave it a name matching the
    // version I selected.
    //
        maven 'maven-3.9.9'
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('test') {
            steps {
                script {
                    echo "Testing the application..."
                    echo "Executing pipeline for branch $BRANCH_NAME"
                }
            }
        }
        stage('build jar') {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage('build and push image') {
            steps {
                script {
                    buildImage 'mannyops/pipeline-demo-app:groovy4.0'
                    dockerLogin()
                    dockerPush 'mannyops/pipeline-demo-app:groovy4.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}