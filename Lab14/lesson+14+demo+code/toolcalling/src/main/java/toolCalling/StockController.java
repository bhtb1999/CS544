package toolCalling;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toolCalling.wallet.WalletTool;

@RestController
public class StockController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private WalletTool walletTool;

    @GetMapping("/companies-in-wallet")
    public String totalValueByWallet(@RequestParam(defaultValue = "A") String wallet) {
        return chatClient.prompt()
                .tools(walletTool)
                .user(u -> u.text("Give the company name from all stocks in my {wallet}").param("wallet",wallet))
                .call()
                .content();
    }

    @GetMapping("/analyse-wallet")
    public String evaluateWallet(@RequestParam(defaultValue = "A") String wallet) {
        return chatClient.prompt()
                .tools(walletTool)
                .user(u -> u.text("Please give a detailed analysis of all stocks in my {wallet}").param("wallet",wallet))
                .call()
                .content();
    }
}
