
def projectName = "DevOps 101 - Stages"

node (ldec5009c1bld01) {
    stage('Prepare Environment'){
        //clone
    }
    stage('Build'){
        echo "Build"
    }
    stage('Test'){
        echo "Test"
    }
    stage('Deploy'){
        echo "Deploy"
    }
}