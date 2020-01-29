pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk-11'
        git 'git'
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Dmaven.test.failure.ignore=false'
                junit '**/target/surefire-reports/*.xml'
                stash includes: '**/target/jacoco.exec', name: 'jacoco'
            }
        }
        stage('Sonar') {
            when {
                branch 'master'
            }
            environment {
                SONAR_TOKEN = credentials('sonar-token')
            }
            steps {
                unstash 'jacoco'

                echo 'SonarQube analyze...'
                sh "mvn sonar:sonar -Dproject.settings=sonar-project.properties -Dsonar.login=$SONAR_TOKEN -X"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Deploy') {
            when {
                branch 'master'
            }
            environment {
                JASYPT_ENCRYPTOR_PASSWORD = credentials('jasypt-encryptor-password')
            }
            steps {
                sshPublisher(
                    continueOnError: false,
                    failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: 'madpill',
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "target/madpill-backend-0.0.1-SNAPSHOT.jar",
                                    removePrefix: "target",
                                ),
                                sshTransfer(
                                    sourceFiles: "**/deploy.sh",
                                    execCommand: "cd /var/www/madpill/madpill-backend/ci && chmod 755 deploy.sh && export JASYPT_ENCRYPTOR_PASSWORD=$JASYPT_ENCRYPTOR_PASSWORD && bash deploy.sh"
                                ),
                            ]
                        )
                    ]
                )
            }
        }
    }
}