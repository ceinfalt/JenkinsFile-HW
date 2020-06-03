
//shared libraries
includes @HybrisAcceleratorLibrary //pretend we have these in the repo - per Ken, will update this with the link in chat window Ken sent about how to use shared libraries

//project variables
def projectName = "hybrisHomework"
//def nexusCredID = "nexusCredIDPlaceholder"
//def nexusURL = "nexusURLPlaceholder" will be a URL followed by :8081
//def nexusRepo = "nexusURLPlaceholder"
//def nexusOrg = "nexusURLPlaceholder"
//def artifactName = "nexusURLPlaceholder"
//def versionID = "nexusURLPlaceholder"
//def server = "nexusURLPlaceholder"
//def appEnvironment = "nexusURLPlaceholder"
//def hybrisComponent = "nexusURLPlaceholder"
//def artifactTag = "nexusURLPlaceholder"
//def sonarEnv = "nexusURLPlaceholder"
def workspace = "${env.WORKSPACE}" //Global Variable within Jenkins
def buildNumber = "${env.BUILD_NUMBER}"  //Global Variable within Jenkins
def branchName = "${env.BRANCH_NAME}"  //Global Variable within Jenkins

//error variables
def prepareFailure = false
def cloneFailure = false
def buildFailure = false
def testFailure = false
def deployFailure = false
def notifyFailure = false

//server variables
def slave = "buildServer123 || buildServer456"

//local function definitions

node (slave) {
    try {
        stage('Prepare Environments'){
                try {
                    //call print Env Variable function
                    //call clean out build server function -in Jenkins syntax generator
                    echo "Build server $slave clean!"
                    //get latest version of hybris
                    echo "Getting Hybris..."
                    getHybris(call) //will replace call with our passed variables
                    echo "Got hybris!"
                }catch{
                    prepareFailure = true;
                    throw err
                }
                //Add try catch loop for clone, Ken adding text to chat
        }
        stage('Checkout Code and Release Notes') {
            try {
                sh "mkdir hybrisClone"
                dir('hybrisClone') {
                    gitClone(buildBranch, credID, repoURL, buildTag) //will need to define credID and repoURL at top, not buildBranch or buildTag
                    sh "cp -a ${env.WORKSPACE}/hybrisClone/. ${env.WORKSPACE}"sh "rm -rf hybrisClone"
                    }
            }
            catch{
                    cloneFailure = true;
                    throw err
                }
        }
    stage('Build'){
           
                //call function for hybrisBuild.groovy
            
                
    }
    stage('Test'){
        echo "Not Testing Yet!"
    }
    stage('Deploy'){
        echo "Deploying..."
        //deploy to 2 servers
            try {
                echo "Deploy Environment"
                parallel (
					"Deploying to $hybrisServerA" :{
                        echo "Deploying to $hybrisServerA"
                        //call hybrisStopServer
                        //call hybrisUpload
                        //call hybrisDeploy -Unpacking code on Server
                    },
                    "Deploying to $hybrisServerB Server B" :{
                        echo "Deploying to $hybrisServerA"
                    }
            }catch{
                deployFailure = true;
                throw err
            }
    }
                    //new notifySuccess stage - call notifySuccess.groovy 
    } catch (err)(
        if (prepareFailure) {
            echo "Prepare Failure"
        } else if (cloneFailure) {
            echo "Test Failure"
        } else if (buildFailure) {
            echo "Build Failure"
            //call notifyBuild.groovy
        } else if (testFailure) {
            echo "Test Failure"
        } else if (deployFailure) {
            echo "Deploy Failure"
        } else {
            echo "Generic Failure - Investigate!"
        }
    )
    }   
}
