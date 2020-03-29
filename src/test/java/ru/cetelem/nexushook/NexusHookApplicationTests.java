package ru.cetelem.nexushook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ru.cetelem.nexushook.service.JenkinsService;
import ru.cetelem.nexushook.service.NexusHookService;
import ru.cetelem.nexushook.web.NexusHookController;

@SpringBootTest
@ContextConfiguration(classes= {NexusHookConfig.class, NexusHookConfigTest.class, 
		NexusHookController.class, NexusHookService.class})
class NexusHookApplicationTests {
	@Autowired
	NexusHookController nexusHookController;
	
	@Test
	void contextLoads() {
	}

	@Test
	void checkMach() throws IOException {
		nexusHookController.webhook(getJson("/test1.json"));
	}

	@Test
	void checkNoMach() throws IOException {
		nexusHookController.webhook(getJson("/test2.json"));
	}
	
	private String getJson(String resourceFileName) throws IOException {
		InputStream inputStream = NexusHookApplicationTests.class.getResource(resourceFileName).openStream();
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String test = s.hasNext() ? s.next() : "";
		return test;
	}
		
}
