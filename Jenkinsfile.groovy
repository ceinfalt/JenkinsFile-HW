
//set variables
def projectName = "hybrisHomework"

node (slave) {
    stage('Prepare Environment'){
        //clone
    }
    stage('Build'){
        echo "Building..."
        //build
    }
    stage('Test'){
        echo "Not Testing Yet!"
    }
    stage('Deploy'){
        echo "Deploying..."
        //deploy to 2 servers
    }
}
