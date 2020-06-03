
def projectName = "DevOps 102 - Try,Catch,Finally"

//errors
def buildFailure = false
def testFailure = false
def deployFailure = false

node {
    try {
        stage('Build'){
            try {
                echo "Build"
            }catch{
                buildFailure = true;
                throw err
            }
        }
        stage('Test'){
            try {
                echo "Test"
            }catch{
                testFailure = true;
                throw err
            }
        }
        stage('Deploy'){
            try {
                echo "Deploy"
            }catch{
                deployFailure = true;
                throw err
            }
        }
    } catch (err)(
        if (buildFailure) {
            echo "Build Failure"
        } else if (testFailure) {
            echo "TestFailure"
        } else if (deployFailure) {
            echo "Deploy Failure"
        } else {
            echo "Generic Failure"
        }
    )
}