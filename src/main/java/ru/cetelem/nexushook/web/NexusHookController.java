package ru.cetelem.nexushook.web;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.cetelem.nexushook.domain.NexusAction;
import ru.cetelem.nexushook.service.NexusHookService;


@Controller
public class NexusHookController {
	private static final Log LOG = LogFactory.getLog(NexusHookController.class); 
	
	private  NexusHookService nexusHookService;
	
	NexusHookController(@Autowired NexusHookService nexusHookService){
		this.nexusHookService = nexusHookService;
	}
	
	@RequestMapping("/")
	public @ResponseBody String webhook(@RequestBody String json) {
		LOG.info(String.format("Webhook hit: %s", json));
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			NexusAction.NexusComponent component = objectMapper.readValue(json, NexusAction.class).getComponent();
			return nexusHookService.hook(component);
		} catch (IOException e) {
			LOG.error(e);
			return "ko";
		}  

		
	}


}
