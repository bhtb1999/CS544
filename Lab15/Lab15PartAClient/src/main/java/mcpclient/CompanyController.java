package mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private final ChatClient chatClient;

    public CompanyController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {

        this.chatClient = chatClientBuilder
                .defaultSystem("Please prioritise context information for answering questions")
                .defaultToolCallbacks(tools)
                .build();
    }

    @GetMapping("/profits-from-month")
    public String profitsFromInstructor(@RequestParam(defaultValue = "John Doe") String month) {
        return chatClient.prompt()
                .user(u -> u.text("Give all profits of this month {month}").param("month", month))
                .call()
                .content();
    }

}
