
//set variables test CE change here

//project variables
def projectName = "hybrisHomework"
//def nexusCredID = "nexusCredIDPlaceholder"
//def nexusURL = "nexusURLPlaceholder"
//def nexusRepo = "nexusURLPlaceholder"
//def nexusOrg = "nexusURLPlaceholder"
//def artifactName = "nexusURLPlaceholder"
//def versionID = "nexusURLPlaceholder"
//def server = "nexusURLPlaceholder"
//def appEnvironment = "nexusURLPlaceholder"
//def hybrisComponent = "nexusURLPlaceholder"
//def artifactTag = "nexusURLPlaceholder"
//def sonarEnv = "nexusURLPlaceholder"
def workspace = ${env.WORKSPACE} //Global Variable within Jenkins
def buildNumber = ${env.BUILD_NUMBER}  //Global Variable within Jenkins
def branchName = ${env.BRANCH_NAME}  //Global Variable within Jenkins

//error variables
def prepareFailure = false
def buildFailure = false
def testFailure = false
def deployFailure = false

//server variables
def hybrisServerA = hybrisServer123
def hybrisServerB = hybrisServer456
def slave = "buildServer123 || buildServer456"

node (slave) {
    try {
    stage('Prepare Environment'){
        //clone
            try {
                echo "Prepare Environment"
            }catch{
                prepareFailure = true;
                throw err
    }
    stage('Build'){
        echo "Building..."
        //build to 2 servers
            try {
                echo "Build"
            }catch{
                buildFailure = true;
                throw err
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
					"Deploy Server A" :{
                        echo "Deploying to Server A"
                    },
                    "Deploy Server B" :{
                        echo "Deploying to Server B"
                    }
            }catch{
                deployFailure = true;
                throw err
    } catch (err)(
        if (buildFailure) {
            echo "Prepare Failure"
        } else if (buildFailure) {
            echo "Build Failure"
        } else if (testFailure) {
            echo "Test Failure"
        } else if (deployFailure) {
            echo "Deploy Failure"
        } else {
            echo "Generic Failure - Investigate!"
        }
    )
}
