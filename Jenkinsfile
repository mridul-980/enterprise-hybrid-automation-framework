pipeline {

    agent any

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge'],
            description: 'Select Browser'
        )

        choice(
            name: 'ENV',
            choices: ['qa', 'uat'],
            description: 'Select Environment'
        )

        choice(
            name: 'HEADLESS',
            choices: ['true', 'false'],
            description: 'Run in Headless Mode'
        )
    }

    stages {

        stage('Checkout') {

            steps {

                echo 'Pipeline Started'
            }
        }

        stage('Execute Tests') {

            steps {

                bat """
                mvn clean test ^
                -Dbrowser=%BROWSER% ^
                -Denv=%ENV% ^
                -Dheadless=%HEADLESS%
                """
            }
        }
    }

    post {

        always {

            echo 'Pipeline Execution Finished'
        }

        success {

            echo 'Tests Passed'
        }

        failure {

            echo 'Tests Failed'
        }
    }
}