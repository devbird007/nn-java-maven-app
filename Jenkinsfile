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
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t mannyops/pipeline-demo-app:1.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push mannyops/pipeline-demo-app:1.0'
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application..."
                }
            }
        }
    }
}