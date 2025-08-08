package prompt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import prompt.domains.Profit;
import prompt.repositories.ProfitRepository;

@SpringBootApplication
public class SimplePromptApplication implements CommandLineRunner {

	@Autowired
	ProfitRepository profitRepository;

	public static void main(String[] args) {
		SpringApplication.run(SimplePromptApplication.class, args);


	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
		ChatClient.Builder builder = ChatClient.builder(chatModel);
		builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
		builder.defaultAdvisors(new MyLoggingAdvisor());
		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDate jan2024 = LocalDate.of(2024, 1, 15);
		LocalDate mar2024 = LocalDate.of(2024, 3, 20);
		LocalDate jun2024 = LocalDate.of(2024, 6, 10);
		
		Date januaryDate = Date.from(jan2024.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date marchDate = Date.from(mar2024.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date juneDate = Date.from(jun2024.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Profit p1 = new Profit("January Profit","Amazon", " New year profit from retail sales", januaryDate, 100);
		Profit p2 = new Profit("March Profit","Meta", " Spring quarter profit from online sales", marchDate, 400);
		Profit p3 = new Profit("June Profit","Google", " Summer profit from wholesale operations", juneDate, 10000);
		
		profitRepository.saveAll(List.of(p1, p2, p3));
	}
}
