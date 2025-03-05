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
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployImage()
                }
            }
        }
    }
}