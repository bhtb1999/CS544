package prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public Answer chat(@RequestParam(value = "message")String message) {
//        OpenAiChatOptions openAiOptions = OpenAiChatOptions.builder()
//                .model("gemini-1.5-flash")
//                .temperature(2.0)
//                .build();

        return chatClient.prompt()
                .system("User will give question about pet healthcare and you should logically answer it")
                .user(message)
//                .options(openAiOptions)
                .call()
                .entity(Answer.class);
    }


}
