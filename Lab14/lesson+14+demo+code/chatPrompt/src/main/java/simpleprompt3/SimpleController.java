package simpleprompt3;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message")String message) {
        OpenAiChatOptions openAiOptions = OpenAiChatOptions.builder()
                .model("gemini-1.5-flash")
                .temperature(2.0)
                .build();

        return chatClient.prompt()
                .user(message)
                .options(openAiOptions)
                .call()
                .content();
    }


}
