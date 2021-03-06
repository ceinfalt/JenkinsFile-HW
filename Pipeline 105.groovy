
//includes shared @Library -var
def projectName = "DevOps 105 - Functions"

//errors
def buildFailure = false
def testFailure = false
def deployFailure = false

def slave = "server1 || server2"

def antDeploy(String serviceName) {
    sh """cd "${env.WORKSPACE}/${serviceName}"
        echo 'Starting Deploy'
        ant deployAll
        """
}

node(slave){
    try {
        stage('Build'){
            try {
                echo "Build $slave";
                serviceName == "service"
                antDeploy(serviceName)
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
                parallel (
					"Deploy Server" :{
                        echo "Step 1"
                    },
                    "step:2" :{
                        echo "Step 2"
                    },
                    "step:3" :{
                        echo "Step 3"
                    },
                    "step:4" :{
                        echo "Step 4"
                    }
                )
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
