pipeline {
  agent any
  stages {
    stage("Compile") {
      steps {
        sh "./gradlew compileJava"
      }
    }
    stage("Unit test") {
      steps {
        sh "./gradlew test"
      }
    }
    stage("Code Coverage") {
      steps {
        sh "./gradlew jacocoTestReport"
        publishHTML (target: [
          reportDir: 'build/reports/jacoco/test/html',
          reportFiles: 'index.html',
          reportName: "JaCoCo Report"
        ])
        sh "./gradlew jacocoTestCoverageVerification"
      }
    }
    stage("Static code analysis") {
      steps {
        sh "./gradlew checkstyleMain"
        publishHTML (target: [
          reportDir: 'build/reports/checkstyle/',
          reportFiles: 'main.html',
          reportName: "Checkstyle Report"
        ])
      }
    }
    stage("Package") {
      steps {
        sh "./gradlew build"
      }
    }

    stage("Docker build") {
      steps {
        sh "docker build -t localhost:5000/ivangonzalezsaenz/calculator ."
      }
    }
    stage("Docker push to local registry") {
      steps {
        withCredentials([usernamePassword(credentialsId: 'LOCAL_DOCKER_REGISTRY_CREDENTIALS', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
          sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin localhost:5000"
          sh "docker push localhost:5000/ivangonzalezsaenz/calculator"
        }
      }
    }
    stage("Deploy to staging") {
      steps {
        sh "docker run -d --rm -p 8765:8081 --name calculator localhost:5000/ivangonzalezsaenz/calculator"
      }
    }
    stage("Acceptance test") {
      steps {
        sleep 60
        //sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
        sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8765"
      }
    }
  }
  post {
    always {
      sh "docker stop calculator"
    }
  }
}