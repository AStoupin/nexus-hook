pipeline {
  agent {
    kubernetes {
		label  'jenkins-slave-label'
		
    }
    
  }
  stages {

 
      
    stage('Build component') {
      steps {
	    container('maven') {
          sh 'mvn clean package  -B -DskipTests'
        }

      }
    }
    stage('Test') {
        steps {
            container('maven') {
                sh 'mvn test'

                junit 'target/surefire-reports/*.xml'

            }
        }
    }
    stage('Build & push image') {
      steps {
        container('docker') {
		 withCredentials([usernamePassword(credentialsId: 'dockerpwd',usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {

		    sh '''
		      docker build --pull -t nexus-hook  .

	          cd target
	          export artifactId=$(ls *.jar)
	          echo artifactId=$artifactId 

		      docker tag nexus-hook astoupin/nexus-hook 
		      docker tag nexus-hook astoupin/nexus-hook:${artifactId}__${BUILD_NUMBER}   
		      
		      docker login -u $USERNAME -p $USERPASS
		      docker push astoupin/nexus-hook
		      docker push astoupin/nexus-hook:${artifactId}__${BUILD_NUMBER}
		    '''
		  }          
          
        }

      }
    }

  	stage ('Deploy') {
        steps {
            build job: 'deployComponent', parameters: [
            string(name: 'deploymentName', value: 'nexus-hook')
            ]
        }
    }   
        
  }
}