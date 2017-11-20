package org.springframework.samples.petclinic.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class PullJobScheduler {
	@Scheduled(fixedDelay = 1000*60*10)
	public void fixedDelayTask() {
		System.out.println(new Date() + " This runs in a fixed delay in 10 minutes");
		Map<String, Integer> vars = new HashMap<String, Integer>();
		RestTemplate restTemplate = new RestTemplate();
		Map res = restTemplate.getForObject(
		  "http://localhost:8080/alan-server/usersFeed.json", 
		  Map.class, vars);
		System.out.println(res);
	}

	@Scheduled(fixedRate = 6000)
	public void fixedRateTask() {
		System.out.println(new Date() + " This runs in a fixed rate");
	}

	@Scheduled(fixedRate = 7000, initialDelay = 2000)
	public void fixedRateWithInitialDelayTask() {
		System.out.println(new Date() + " This runs in a fixed delay with a initial delay");
	}

	@Scheduled(cron = "10 * * * * *")
	public void cronTask() {
		System.out.println(new Date() + " This runs in a cron schedule");
	}
}