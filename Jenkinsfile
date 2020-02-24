pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk-11'
        git 'git'
    }
    stages {
        // 不是推送到 master 分支上时，更新数据库 schema
        stage('Update-DB') {
            when {
                not {
                    branch 'master'
                }
            }
            environment {
                MYSQL_PASSWORD = credentials('mysql-madpill-password')
            }
            steps {
                sh " mysql -Dmadpill_test -umadpill -p'$MYSQL_PASSWORD' < ./src/main/resources/sql/schema.sql"
            }
        }
        stage('Test') {
            options {
                retry(2)
            }
            environment {
                JASYPT_ENCRYPTOR_PASSWORD = credentials('jasypt-encryptor-password')
                MYBATIS_GENERATOR_JDBC_PASSWORD = credentials('mysql-madpill-password')
            }
            steps {
                sh "mvn mybatis-generator:generate -Dmybatis.generator.jdbcURL=jdbc:mysql://34.92.23.92:3306/madpill_test \
                                                   -Dmybatis.generator.jdbcUserId=madpill \
                                                   -Dmybatis.generator.jdbcPassword='$MYBATIS_GENERATOR_JDBC_PASSWORD'"
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