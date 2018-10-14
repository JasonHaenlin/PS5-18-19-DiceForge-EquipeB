pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        echo 'Checkout'
      }
    }
    stage('Build') {
      steps {
        echo 'Clean Build'
        sh 'mvn clean compile'
      }
    }
    stage('Test JUnit') {
      steps {
        parallel(
          test1: {
            echo 'Testing n°1'
            sh 'mvn test'
          },
          test2: {
            echo 'Testing n°2'
            sh 'mvn test'
          }
        )
      }
    }
    stage('Test Mutation') {
      steps {
        echo 'PiTest Mutation'
        sh 'mvn -Dthreads=4 org.pitest:pitest-maven:mutationCoverage'
      }
    }
    stage('Sonarqube') {
      agent any
      steps {
        withSonarQubeEnv('Sonarqube_Local_diceforge') {
          sh 'mvn clean package sonar:sonar -Dsonar.pitest.mode=reuseReport'
        }

      }
    }
    stage('Quality Gate') {
      steps {
        timeout(time: 1, unit: 'HOURS') {
          waitForQualityGate true
        }

      }
    }
    stage('Package') {
      steps {
        echo 'Packaging'
        sh 'mvn package -DskipTests'
      }
    }
    stage('Done') {
      steps {
        echo 'Done'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/**/*', fingerprint: true
      junit 'target/surefire-reports/*.xml'
      echo 'JENKINS PIPELINE'

    }

    success {
      echo 'JENKINS PIPELINE SUCCESSFUL'

    }

    failure {
      echo 'JENKINS PIPELINE FAILED'

    }

    unstable {
      echo 'JENKINS PIPELINE WAS MARKED AS UNSTABLE'

    }

    changed {
      echo 'JENKINS PIPELINE STATUS HAS CHANGED SINCE LAST EXECUTION'

    }

  }
}