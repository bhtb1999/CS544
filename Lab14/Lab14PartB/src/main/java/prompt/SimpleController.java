package prompt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prompt.domains.Product;
import prompt.repositories.ProductRepository;

@RestController
public class SimpleController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/chat")
    public Answer chat(@RequestParam(value = "message") String message) {
        List<Product> products = productRepository.findAll();

        String productInfo = products.stream()
                .map(product -> String.format("- %s: %s", product.getName(), product.getDescription()))
                .collect(Collectors.joining("\n"));

        String enhancedUserMessage = String.format(
                "Available products:\n%s\n\nUser question: %s\n\n" +
                        "Please answer the user's question and recommend relevant products if applicable.",
                productInfo, message);

        return chatClient.prompt()
                .system("User will give question about products in product catalog and you should logically answer it")
                .user(enhancedUserMessage)
                .call()
                .entity(Answer.class);
    }

}
