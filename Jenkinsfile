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
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    gv.deployImage()
                }
            }
        }
    }
}