package functionCalling;

import functionCalling.stock.StockTool;
import functionCalling.wallet.WalletTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    private ChatClient chatClient;

    @Autowired
    private WalletTool walletTool;

    @Autowired
    private StockTool stockTool;

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
                .tools(stockTool)
                .user(u -> u.text("Please give a detailed analysis of all stocks in my {wallet}").param("wallet",wallet))
                .call()
                .content();
    }

    @GetMapping("/stockprice")
    public String getstockprice(@RequestParam(defaultValue = "MSFT") String ticker) {
        return chatClient.prompt()
                .tools(stockTool)
                .user(u -> u.text("Get the stock price of the ticker {ticker}").param("ticker",ticker))
                .call()
                .content();
    }

    @GetMapping("/walletvalue")
    public String walletValue(@RequestParam(defaultValue = "A") String wallet) {
        return chatClient.prompt()
                .tools(walletTool)
                .tools(stockTool)
                .user(u -> u.text("Please give me the stock price of all stocks in my {wallet} . Also compute the total value of the given wallet").param("wallet",wallet))
                .call()
                .content();
    }
}
