package ru.cetelem.nexushook.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cetelem.nexushook.NexusHookConfig;
import ru.cetelem.nexushook.domain.NexusAction.NexusComponent;


@Service
public class NexusHookService {
	private static final Log LOG = LogFactory.getLog(NexusHookService.class); 
	
	
	private NexusHookConfig nexusHookConfig;
	private JenkinsService jenkinsService;
	private ModuleInfoService moduleInfoService;
	
	@Autowired 
	public NexusHookService(NexusHookConfig nexusHookConfig, 
			JenkinsService jenkinsService){
		this.nexusHookConfig = nexusHookConfig;
		this.jenkinsService = jenkinsService;

	}
	
	public String hook(NexusComponent component) {
		LOG.info(String.format("hook started for %s/%s", component.getGroup(), component.getName()));
		
		String jobName = nexusHookConfig.jobByHookComponent(component.getGroup(), component.getName());
		
		if("".equals(jobName)) {
			LOG.info(String.format("no mach for %s/%s", component.getGroup(), component.getName()));
			return "ok";
		}
					
		LOG.info("Match job: " + jobName);
		
		try {
			jenkinsService.executeJob(jobName, component);
		} catch (ParseException | IOException e) {
			LOG.error(e);
			return "ko - " + e.getMessage();
		}

		return "ok";
	}
	
	
	
}
