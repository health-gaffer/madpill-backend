pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk-11'
        git 'git'
    }
    stages {
        stage('Test') {
            environment {
                JASYPT_ENCRYPTOR_PASSWORD = credentials('jasypt-encryptor-password')
                MYBATIS_GENERATOR_JDBC_PASSWORD = credentials('mybatis-generator-jdbcPassword')
            }
            steps {
                sh 'mvn mybatis-generator:generate -Dmybatis.generator.jdbcPassword=$MYBATIS_GENERATOR_JDBC_PASSWORD'
                sh 'mvn clean test -Djasypt.encryptor.password=$JASYPT_ENCRYPTOR_PASSWORD -Dmaven.test.failure.ignore=false'
                junit '**/target/surefire-reports/*.xml'
                stash includes: '**/target/jacoco.exec', name: 'jacoco'
                stash includes: '**/domain/', name: 'domains'
                stash includes: '**/mapper/', name: 'mappers'
            }
        }
        stage('Sonar') {
            when {
                branch 'dev'
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
                unstash 'domains'
                unstash 'mappers'
                sh 'mvn package -DskipTests'
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