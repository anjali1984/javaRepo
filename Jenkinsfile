#!/usr/bin/env groovy

// @Library("com.optum.jenkins.pipeline.library")
@Library("Tops-Shared-Library") _
import com.optum.jenkins.pipeline.library.maven.MavenBuild
import com.optum.jenkins.pipeline.library.sonar.Sonar
import com.optum.jenkins.pipeline.library.openshift.OpenShift
import com.optum.jenkins.pipeline.library.notification.Notifications
import com.optum.jenkins.pipeline.library.notification.Status
import com.optum.jenkins.pipeline.library.docker.Docker
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// import com.optum.jenkins.pipeline.library.scm.Git


// This is an example Jenkinfile where we build and then execute a Sonar Scan
// The Jenkins Global Pipeline Library that is being used is: https://github.optum.com/jenkins-pipelines/global-pipeline-library

// Selecting a known Jenkins Build Slave
pipeline {

  agent {
    label 'docker-maven-slave'
  }

  environment {

    OCP_APP =  'hc1'
    OCP_BUILD =  ''
    DOCKERTAG = 'TEST'
    ARTF_CREDS = 'tops_artifactory'

    JENKINS_CREDS = 'tops_ose'
    DOCKER_NAMESPACE = 'tops-soa'

    OSE_URL = 'https://ose-elr-core.optum.com'
    OSE_PROJ = 'tops-java-modernization'
    OSE_PORT = '8080'

    AUTHOR_NAME = 'TOPS Modernization'
    AUTHOR_EMAIL = 'tops-modernization@uhg.flowdock.com'
  }

  stages {

    stage('Git Checkout') {
      steps {

        echo '-------- Start of Checkout ---------'

        glGitCheckout()

      }
    }
    stage('Maven Build') {
      steps {
        echo '-------- In the Maven Build Stage --------'

        glMavenBuild additionalProps: ['ci.env':''] // ci.env needed if using UHG parent pom
      }
    }
    stage('Sonar Scan') {
      steps {
        echo '-------- In the Sonar Stage --------'

        glSonarMavenScan productName: "TOPS-Modernization",
                          projectName: OCP_APP
      }
    }
    stage('Artifactory Deploy') {
      steps {
        echo '-------- In the Artifactory Stage --------'

        // See https://github.optum.com/jenkins-pipelines/global-pipeline-library/blob/v0.1.17/src/com/optum/jenkins/pipeline/library/maven/MavenBuild.groovy
        // You must use the service account that has write access to your artifactory foldee this article for more information: https://hubconnect.uhg.com/docs/DOC-55607

        glMavenArtifactoryDeploy artifactoryUserCredentialsId: ARTF_CREDS,
                                  additionalProps: ['ci.env':'']
      }
    }
    stage('Docker Push') {
      steps {
        echo '-------- In the Docker Routine --------'

        glDockerRepoCreate dockerCredentialsId: JENKINS_CREDS,
                            namespace: DOCKER_NAMESPACE,
                            repository: OCP_APP

        glDockerImageBuildPush dockerCredentialsId: JENKINS_CREDS,
                                tag: "$DOCKER_NAMESPACE/$OCP_APP:$DOCKERTAG"
      }
    }
    stage('Move to OSE') {
      environment {
        DOCKER_IMAGE_LINK = "docker.optum.com/$DOCKER_NAMESPACE/$OCP_APP:$DOCKERTAG"
      }
      steps {
        echo '-------- In the OSE Move Stage --------'
        echo 'deploying this to OSE'

        glOpenshiftDeploy credentials: JENKINS_CREDS,
                          dockerImage: DOCKER_IMAGE_LINK,
                          project: OSE_PROJ,
                          ocpUrl: OSE_URL,
                          port: OSE_PORT,
                          serviceName: "$OCP_APP-$DOCKERTAG"
      }
    }
    // stage('OSE Deploy Status'){
    //   steps {
    //     echo "----------- Checking Deploy Status -----------"
    //     notifyCheckDeployStatus credentials: JENKINS_CREDS,
    //                             project_name: OSE_PROJ,
    //                             service_name: "$OCP_APP-$DOCKERTAG",
    //                             server_name: OSE_URL
    //
    //   }
    // }
    stage('Prod approval'){
      environment {
        DOCKERTAG = "PROD"
        DOCKER_IMAGE_LINK = "docker.optum.com/$DOCKER_NAMESPACE/$OCP_APP:$DOCKERTAG"
      }
      steps{
        input "Deploy to prod?"

        glDockerImageBuildPush dockerCredentialsId: JENKINS_CREDS,
                               tag: "$DOCKER_NAMESPACE/$OCP_APP:$DOCKERTAG"

        echo '-------- In the OSE Move Stage --------'
        echo 'deploying this to OSE'

        glOpenshiftDeploy credentials: JENKINS_CREDS,
                          dockerImage: DOCKER_IMAGE_LINK,
                          project: OSE_PROJ,
                          ocpUrl: OSE_URL,
                          port: OSE_PORT,
                          serviceName: "$OCP_APP-$DOCKERTAG"
      }
    }
  }

  post {
      always {
        echo 'This will always run'
      }
      success {
          echo 'This will run only if successful'
      }
      failure {
          echo 'This will run only if failed'
      }
      unstable {
          echo 'This will run only if the run was marked as unstable'
      }
      changed {
          echo 'This will run only if the state of the Pipeline has changed'
          echo 'For example, if the Pipeline was previously failing but is now successful'
      }
      aborted {
          echo 'No approval'
          notifyEmail type: 'Prod Approval Denied',
                      message: 'Approval for Production was denied',
                      author_name: AUTHOR_NAME,
                      author_email: AUTHOR_EMAIL,
                      app_name: OCP_APP,
                      job_url: JOB_URL

      }
  }

}
