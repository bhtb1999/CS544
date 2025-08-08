package prompt.tools;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prompt.domains.Profit;
import prompt.domains.ProfitRequest;
import prompt.domains.ProfitResponse;
import prompt.repositories.ProfitRepository;

@Service
public class ProfitTool {

    @Autowired
    private ProfitRepository profitRepository;

    @Tool(description = "Get the profits from specific month. Use month names like 'January', 'March', 'June'")
    public ProfitResponse getProfits(ProfitRequest request) {
        System.out.println("Get profits from specific month: " + request.month());

        List<Profit> allProfits = profitRepository.findAll();

        List<Profit> filteredProfits = allProfits.stream()
                .filter(profit -> profit.getName().toLowerCase().contains(request.month().toLowerCase()))
                .collect(Collectors.toList());

        if (filteredProfits.isEmpty()) {
            return new ProfitResponse(request, "No profits found for " + request.month(), allProfits);
        }

        String detailedMessage = filteredProfits.stream()
                .map(profit -> String.format("Company: %s, Amount: $%d, Description: %s",
                        profit.getCompany(), profit.getAmount(), profit.getDescription()))
                .collect(Collectors.joining("; "));

        return new ProfitResponse(request,
                "Found " + filteredProfits.size() + " profit(s) for " + request.month() + ": " + detailedMessage,
                filteredProfits);
    }
}
