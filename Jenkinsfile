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
    stage('Sonar') {
      steps {
        sh 'Sonar Scanner'
        sh 'mvn sonar:sonar -Dsonar.host.url=https://sonar.jareon.fr -Dsonar.login=8b66d7cae71e2b72608e08baa2051f5b22361966'
      }
    }
    stage('Package') {
      steps {
        echo 'Packaging'
        sh 'mvn package -DskipTests'
      }
    }
    stage('End') {
      steps {
        echo 'Done'
      }
    }
  }
}