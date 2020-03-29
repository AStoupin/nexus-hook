package ru.cetelem.nexushook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.cetelem.nexushook.domain.HookInfo;


@Configuration
@ConfigurationProperties("nexus")
@EnableConfigurationProperties
public class NexusHookConfig {
	private static final Log LOG = LogFactory.getLog(NexusHookConfig.class); 

	private List<HookInfo> hooks;
	
	@PostConstruct
	private void postConstruct() {
		LOG.info("Hook params:");
		if(hooks == null) {
			LOG.warn("No hook params. Create empty list");
			hooks = new ArrayList();
			return ;
		}
		hooks.forEach(h->LOG.info(h.toString()));
	}

	public List<HookInfo> getHooks() {
		return hooks;
	}

	public void setHooks(List<String> hooks) {
		this.hooks = hooks.stream().map(s->
			new HookInfo(s.split(":")[0],
						 s.split(":")[1])).collect(Collectors.toList());
	}

	public String jobByHookComponent(String  groupId, String nameId){
		Optional<HookInfo> hook =  hooks.stream()
				.filter(h->h.getComponentName().equalsIgnoreCase(groupId+ "/" + nameId)).findFirst();

		if(!hook.isPresent())
			return ""; 
		
		return hook.get().getJobName();
	}
}
