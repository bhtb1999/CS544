package prompt.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prompt.domains.ConvertResponse;
import prompt.domains.Profit;
import prompt.repositories.ProfitRepository;

@Service
public class ConvertTool {

    @Autowired
    private ProfitRepository profitRepository;

    private static final Map<String, Double> EXCHANGE_RATES = Map.of(
            "EUR", 0.85,
            "GBP", 0.73,
            "JPY", 110.0,
            "CNY", 6.45,
            "INR", 74.5,
            "CAD", 1.25,
            "AUD", 1.35);

    @Tool(description = "Convert dollar amount to given currency. Provide the target currency code (EUR, GBP, JPY, etc.)")
    public ConvertResponse convert(String month, String company, String targetCurrency) {
        System.out.println("Converting profit for: " + company + " in " + month + " to " + targetCurrency);

        List<Profit> allProfits = profitRepository.findAll();

        List<Profit> filteredProfits = allProfits.stream()
                .filter(profit -> profit.getName().toLowerCase().contains(month.toLowerCase())
                        && profit.getCompany().toLowerCase().contains(company.toLowerCase()))
                .collect(Collectors.toList());

        if (filteredProfits.isEmpty()) {
            return new ConvertResponse("No profits found for " + company + " in " + month);
        }

        Profit profit = filteredProfits.get(0);
        double dollarAmount = profit.getAmount();

        String upperCurrency = targetCurrency.toUpperCase();
        if (!EXCHANGE_RATES.containsKey(upperCurrency)) {
            return new ConvertResponse(
                    "Currency " + targetCurrency + " not supported. Supported: EUR, GBP, JPY, CNY, INR, CAD, AUD");
        }

        double exchangeRate = EXCHANGE_RATES.get(upperCurrency);
        double convertedAmount = dollarAmount * exchangeRate;

        BigDecimal rounded = new BigDecimal(convertedAmount).setScale(2, RoundingMode.HALF_UP);

        String message = String.format("Company: %s, Month: %s, Original: $%.2f USD, Converted: %.2f %s (Rate: %.3f)",
                profit.getCompany(), month, dollarAmount, rounded.doubleValue(), upperCurrency, exchangeRate);

        return new ConvertResponse(message);
    }
}
