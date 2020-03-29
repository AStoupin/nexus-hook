package ru.cetelem.nexushook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.cetelem.nexushook.web.NexusHookController;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class NexusHookApplicationTests {
	@Autowired
	NexusHookController nexusHookController;
	
	@Test
	void contextLoads() {
	}

	@Test
	void checkMach() throws IOException {
		InputStream inputStream = NexusHookApplicationTests.class.getResource("/test1.json").openStream();
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String test1 = s.hasNext() ? s.next() : "";
		
		nexusHookController.webhook(test1);
	}

	@Test
	void checkNoMach() throws IOException {
		InputStream inputStream = NexusHookApplicationTests.class.getResource("/test2.json").openStream();
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String test1 = s.hasNext() ? s.next() : "";
		
		nexusHookController.webhook(test1);
	}
		
}
