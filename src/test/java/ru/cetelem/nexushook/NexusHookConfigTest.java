package ru.cetelem.nexushook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.*;

import ru.cetelem.nexushook.service.JenkinsService;
import ru.cetelem.nexushook.service.NexusHookService;

@Configuration
//@ComponentScan({"ru.cetelem.nexushook.web"})
public class NexusHookConfigTest {
	/*
	@Bean
	public JenkinsService jenkinsService() {
		return  mock(JenkinsService.class);
		
	}
	
	
	@Bean
	public NexusHookService nexusHookService(@Autowired NexusHookConfig nexusHookConfig) {
		return new NexusHookService(nexusHookConfig,  jenkinsService());
		
	}
*/	
	
}
