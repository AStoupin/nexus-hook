package ru.cetelem.nexushook.service;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.cetelem.nexushook.domain.NexusAction.NexusComponent;

@Service
public class JenkinsService {
	private static final Log LOG = LogFactory.getLog(JenkinsService.class); 
	
	private String jenkinsUrl;
	private String jenkinsLogin;
	private String jenkinsPassword;
	
	private final String ORIGIN_MESSAGE ="Cause%20nexus%20hook%20trigger";
	
	public JenkinsService(@Autowired @Value("${jenkins.url}") String jenkinsUrl,
			@Autowired @Value("${jenkins.login}") String jenkinsLogin,
			@Autowired @Value("${jenkins.password}") String jenkinsPassword) {

		this.jenkinsUrl = jenkinsUrl;
		this.jenkinsLogin = jenkinsLogin;
		this.jenkinsPassword = jenkinsPassword;
	}
	
    public void authenticate(HttpPost httppost, String user, String password){
        String encoding = new String(Base64.encodeBase64((user+":"+password).getBytes()));
        httppost.setHeader("Authorization", "Basic " + encoding);
    }
    
	public void executeJob(String jobName, NexusComponent component) throws ParseException, IOException {
		LOG.info("executeJob: " + jobName);
		
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		
		HttpPost httppost = new HttpPost(String.format("%s/job/%s/buildWithParameters?groupId=%s&artifactId=%s&version=%s&cause=%s", 
				jenkinsUrl, jobName, component.getGroup(), component.getName(), component.getVersion(),
				ORIGIN_MESSAGE));
		authenticate(httppost, jenkinsLogin, jenkinsPassword);

        HttpResponse response = httpclient.execute(httppost);
        
        LOG.info("executing request " + httppost.getRequestLine());

        String statusLine = response.getStatusLine().toString();
        LOG.info("executing satus " + statusLine);
        
  
            
        
	}
}
