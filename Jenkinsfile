pipeline {

    agent any
    parameters {

    choice(
        name: 'BROWSER',
        choices: ['chrome', 'firefox', 'edge'],
        description: 'Select browser')

    choice(
        name: 'ENV',
        choices: ['qa', 'uat'],
        description: 'Select environment')

    choice(
        name: 'EXECUTION',
        choices: ['local', 'remote'],
        description: 'Execution type')

    booleanParam(
        name: 'HEADLESS',
        defaultValue: true,
        description: 'Run browser in headless mode')
}

   tools {
    maven 'Maven-3.9.16'
    jdk 'JDK17'
}

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
		
		stage('Initialize') {

    steps {

        script {

            currentBuild.displayName =
                    "#${BUILD_NUMBER} | ${params.BROWSER} | ${params.ENV}"

            echo "Browser   : ${params.BROWSER}"
            echo "Environment : ${params.ENV}"
            echo "Execution : ${params.EXECUTION}"
            echo "Headless  : ${params.HEADLESS}"
        }
    }
}

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

     stage('Build') {
    steps {
        bat 'mvn clean compile -DskipTests'
    }
}

        stage('Run Tests') {
            steps {
               bat """
mvn test ^
-Dbrowser=%BROWSER% ^
-Denv=%ENV% ^
-Dexecution=%EXECUTION% ^
-Dheadless=%HEADLESS%
"""
            }
        }
stage('SonarQube Analysis') {
    steps {
        withSonarQubeEnv('SonarQube') {
            withCredentials([string(
                    credentialsId: 'sonarqube-token',
                    variable: 'SONAR_TOKEN')]) {

              bat """
mvn verify sonar:sonar ^
-Dsonar.token=%SONAR_TOKEN%
"""
            }
        }
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