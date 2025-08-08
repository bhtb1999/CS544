package functionCalling.wallet;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WalletTool {
    private List<Stock> walletA = new ArrayList<>();
    private List<Stock> walletB = new ArrayList<>();

    public WalletTool() {
        walletA.add(new Stock("MSFT",100));
        walletA.add(new Stock("AMZN",50));
        walletB.add(new Stock("AAPL",500));
    }

    @Tool(description = "Get the stock ticker and quantity from my wallet")
    public WalletResponse getStockInfoFromWallet(WalletRequest walletRequest) {
        System.out.println("WalletTool is called with "+walletRequest.walletName());
        if (walletRequest.walletName().equals("A")) return new WalletResponse(walletA);
        if (walletRequest.walletName().equals("B")) return new WalletResponse(walletB);
        return null;
    }
}