package prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import prompt.domains.Product;
import prompt.repositories.ProductRepository;

import java.util.List;

@SpringBootApplication
public class SimplePromptApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

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
		Product product = new Product("Pencil", "The Pencil");
		Product product2 = new Product("Pencil2", "The Pencil2");
		Product product3 = new Product("Pencil3", "The Pencil3");
		Product product4 = new Product("Pencil4", "The Pencil4");
		productRepository.saveAll(List.of(product, product2, product3, product4));
	}
}
