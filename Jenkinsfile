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
                sh "mvn sonar:sonar -Dsonar.projectKey=cn.edu.nju:madpill-backend -Dsonar.host.url=http://34.92.23.92:9000 -Dsonar.login=$SONAR_TOKEN -X"
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
                                    sourceFiles: "**/madpill-backend-0.0.1-SNAPSHOT.jar",
                                    removePrefix: "target",
                                ),
                                sshTransfer(
                                    sourceFiles: "**/deploy.sh",
                                    execCommand: "cd /var/www/madpill/madpill-backend && chmod 755 deploy.sh && bash deploy.sh"
                                ),
                            ]
                        )
                    ]
                )
            }
        }
    }
}