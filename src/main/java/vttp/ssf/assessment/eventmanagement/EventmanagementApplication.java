package vttp.ssf.assessment.eventmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	@Autowired
	DatabaseService dbSvc;

	@Autowired
	RedisRepository repo;

	@Override
	public void run(String... args) throws Exception {

		String fileName = "events.json";
		for (Event event : dbSvc.readFile(fileName)) {
			
			repo.saveRecord(event);
		}
	}
}
