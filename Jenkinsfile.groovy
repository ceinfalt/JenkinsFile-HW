
//set variables

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
def buildFailure = false
def testFailure = false
def deployFailure = false

node (slave) {
    try {
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
    } catch (err)(
        if (buildFailure) {
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
