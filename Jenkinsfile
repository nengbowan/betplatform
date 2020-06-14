pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/nengbowan/betplatform.git', branch: 'master', credentialsId: 'fenggogit')
      }
    }
    stage('package') {
      steps {
        sh '''mvn versions:set -DnewVersion=develop-37
'''
        sh 'mvn clean package -Dmaven.test.skip=true -Dfenggo.version=develop-37 -Ddocker.registry=10.33.180.201 -Ddocker.username=admin -Ddocker.password=Harbor12345 -P test'
      }
    }
    stage('deploy') {
      steps {
        sh '''docker service update --replicas=1 legendshop-admin-web --image harbor.odbpo.com/xieyin/legendshop-admin-web:develop-37 --force
'''
        sh '''docker service update --replicas=1 legendshop-admin-web --image harbor.odbpo.com/xieyin/legendshop-admin-web:develop-37 --force
'''
      }
    }
  }
}