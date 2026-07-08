pipeline {

    agent any

   tools {
    maven 'Maven-3.9.16'
    jdk 'JDK17'
}

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'allure generate target/allure-results --clean -o target/allure-report'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
            }
        }
    }

    post {

        always {

            junit 'target/surefire-reports/*.xml'

            echo 'Pipeline execution completed.'
        }

        success {

            echo 'Build Successful.'
        }

        failure {

            echo 'Build Failed.'
        }
    }
}