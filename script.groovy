def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t mannyops/pipeline-demo-app:push1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push mannyops/pipeline-demo-app:push1.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this