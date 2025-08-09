package prompt.tools;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prompt.domains.ConvertResponse;
import prompt.domains.Profit;
import prompt.repositories.ProfitRepository;

@Service
public class CompanyTool {

    @Autowired
    private ProfitRepository profitRepository;

    @Tool(description = "Get the profits from specific company for a specific month")
    public ConvertResponse getCompanyProfit(String month, String company) {
        System.out.println("Getting profit for company: " + company + " in month: " + month);

        List<Profit> allProfits = profitRepository.findAll();

        List<Profit> filteredProfits = allProfits.stream()
                .filter(profit -> profit.getName().toLowerCase().contains(month.toLowerCase())
                        && profit.getCompany().toLowerCase().contains(company.toLowerCase()))
                .collect(Collectors.toList());

        if (filteredProfits.isEmpty()) {
            return new ConvertResponse("No profits found for " + company + " in " + month);
        }

        Profit profit = filteredProfits.get(0);
        String message = String.format("Company: %s, Month: %s, Profit: $%d, Description: %s",
                profit.getCompany(), month, profit.getAmount(), profit.getDescription());

        return new ConvertResponse(message);
    }
}
