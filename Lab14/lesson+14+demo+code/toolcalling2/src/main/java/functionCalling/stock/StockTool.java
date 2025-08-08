package functionCalling.stock;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class StockTool {

    @Tool(description = "Get the stock price from a stock ticker")
    public StockResponse getPriceFromStockTicker(StockRequest stockRequest) {
        if (stockRequest.ticker().equals("MSFT")) return new StockResponse(151.24F);
        if (stockRequest.ticker().equals("AMZN")) return new StockResponse(221.45F);
        return new StockResponse(0.0F);
    }
}