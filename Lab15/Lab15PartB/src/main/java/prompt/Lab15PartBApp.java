package prompt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import prompt.services.HouseService;

@SpringBootApplication
public class Lab15PartBApp implements CommandLineRunner {

	private HouseService houseService;

	public static void main(String[] args) {
		SpringApplication.run(Lab15PartBApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Initializing house search service...");
		if (houseService != null) {
			houseService.populateSampleData();
		}
		System.out.println("House search service initialized with sample data.");
	}
}
