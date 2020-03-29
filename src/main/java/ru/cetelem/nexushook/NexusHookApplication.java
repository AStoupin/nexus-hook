package ru.cetelem.nexushook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import ru.cetelem.nexushook.service.JenkinsService;
import ru.cetelem.nexushook.service.NexusHookService;
import ru.cetelem.nexushook.web.NexusHookController;


@SpringBootApplication
@ComponentScan({"ru.cetelem.nexushook"})
public class NexusHookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexusHookApplication.class, args);
	}

}
