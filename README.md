# Nexus-hook (webhook) for Jenkins

based on articles<br/>
  * https://help.sonatype.com/repomanager3/webhooks 
  * https://arghya.xyz/articles/triggering-jenkins-job-remotely


use application.properties to access to the specified Jenkins instance <br/>
- jenkins.url=http://jenkins.host
- jenkins.login=somelogin <br/>
- jenkins.password=somepassword <br/>

specify a list of hooks
- nexus.hooks[0]=g1/a1:test 
- nexus.hooks[1]=g2/a2:jenknsJob2

where <br/>
- g1 - groupId in Nexus
- a1 - artifactId in Nexus
- test - Jenkins job to be executed (has 3 string params groupId, artifactId, version)


