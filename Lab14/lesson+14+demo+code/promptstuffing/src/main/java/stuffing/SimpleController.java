package stuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class SimpleController {
    @Value("classpath:/profit2024.txt")
    private Resource profit2024Resource;
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message")String message) throws IOException {
        String profit2024 = profit2024Resource.getContentAsString(Charset.defaultCharset());
        return chatClient.prompt()
                .system("This is the monthly profit of my company in 2024: "+profit2024)
                .user(message)
                .call()
                .content();
    }
}
