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
    stage('Test') {
      steps {
        echo 'Testing'
        sh 'mvn test'
      }
    }
    stage('Sonarqube') {
      agent any
      steps {
        withSonarQubeEnv('Sonarqube_Local_diceforge') {
          sh 'mvn clean package sonar:sonar'
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