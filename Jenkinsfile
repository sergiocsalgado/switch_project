
pipeline {
    agent any

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building...'
                sh 'mvn -DskipTests clean package'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Publish Jacoco Report') {
            steps {
                echo 'Publishing Jacoco Report...'
                jacoco(execPattern: '**/target/jacoco.exec')
            }
            post {
                always {
                    jacoco(execPattern: '**/target/jacoco.exec')
                    jacoco(classPattern: '**/target/classes')
                    jacoco(sourcePattern: 'src/main/java')
                    publishCoverage(adapters: [
                        jacocoAdapter('*/target/site/jacoco/*.xml')
                    ])
                }
            }
        }

        stage('Generate Javadocs') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Generating Javadocs...'
                script {
                    try {
                        sh 'mvn javadoc:javadoc'
                    } catch (Exception e) {
                        echo 'Error generating Javadocs, but the build will continue'
                    }
                }
            }
        }

        stage('Publish Javadoc') {

            steps {
                echo 'Publishing Javadoc...'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site/apidocs',
                    reportFiles: 'index.html',
                    reportName: 'Javadoc'
                ])
            }
        }

        stage('Archive') {
            steps {
                echo 'Archiving...'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                archiveArtifacts artifacts: 'target/site/apidocs/**', fingerprint: true
                archiveArtifacts artifacts: 'target/surefire-reports/**', fingerprint: true
                archiveArtifacts artifacts: 'docs/**', fingerprint: true
            }
        }

        stage('Publish Image') {
            steps {
                echo 'Building and publishing Docker image...'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerHubID') {
                        def frontendImage = docker.build("wolffer22/frontend:${env.BUILD_ID}", "-f ./devops/frontend/Dockerfile .")
                        def backendImage = docker.build("wolffer22/backend:${env.BUILD_ID}", "-f ./devops/backend/Dockerfile .")

                        frontendImage.push()
                        frontendImage.push("latest")

                        backendImage.push()
                        backendImage.push("latest")
                    }
                }
            }
        }
    }
}
