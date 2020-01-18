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
                    continueOnError: false, failOnError: true,
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