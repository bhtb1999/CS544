package finalproject.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.lexruntimev2.model.*;

import java.util.UUID;

@Service
public class LexService {

    private final LexRuntimeV2Client lexClient;
    private String mySessionId;


    @Value("${aws.botId}")
    private String botId;

    @Value("${aws.botAliasId}")
    private String botAliasId;

    @Value("${aws.localeId}")
    private String localeId;

    public LexService(LexRuntimeV2Client lexClient) {
        this.lexClient = lexClient;
    }

    public String sendTextToLex(String text) {
        if(mySessionId == null) {
            mySessionId = UUID.randomUUID().toString();
        }
        RecognizeTextRequest request = RecognizeTextRequest.builder()
                .botId(botId)
                .botAliasId(botAliasId)
                .localeId(localeId)
                .sessionId(mySessionId)
                .text(text)
                .build();

        RecognizeTextResponse response = lexClient.recognizeText(request);
        mySessionId = response.sessionId();
        if (!response.messages().isEmpty()) {
            return response.messages().get(0).content();
        } else {
            return "Booking completed";
        }
    }
}
