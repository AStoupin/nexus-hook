# Nexus-hook (webhook) for Jenkins

The service hooks new artifacts in Nexus and executes a specified Jenkins job  

based on articles<br/>
  * https://help.sonatype.com/repomanager3/webhooks 
  * https://arghya.xyz/articles/triggering-jenkins-job-remotely


use application.properties to modify a specific Jenkins instance <br/>
- jenkins.url=http://jenkins.host
- jenkins.login=somelogin <br/>
- jenkins.password=somepassword <br/>

and specify a list of hooks
- nexus.hooks[0]=g1/a1:jenknsJob1 
- nexus.hooks[1]=g2/a2:jenknsJob2

where <br/>
- g1 - groupId in Nexus
- a1 - artifactId in Nexus
- jenknsJob1 - Jenkins job to be executed (has 3 string params: groupId, artifactId, version)


