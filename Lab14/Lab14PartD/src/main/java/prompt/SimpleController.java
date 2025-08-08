package prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prompt.repositories.ProfitRepository;
import prompt.tools.CompanyTool;
import prompt.tools.ConvertTool;
import prompt.tools.ProfitTool;

@RestController
public class SimpleController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ProfitRepository profitRepository;
    @Autowired
    private ProfitTool profitTool;
    @Autowired
    private ConvertTool convertTool;
    @Autowired
    private CompanyTool companyTool;

    @GetMapping("/profit")
    public String profit(@RequestParam(value = "month") String month) {
        System.out.println("Profit endpoint called with month: " + month);
        return chatClient.prompt()
                .tools(profitTool)
                .user(u -> u.text("Give me a profit from {month}").param("month", month))
                .call()
                .content();
    }

    @GetMapping("/company")
    public String company(@RequestParam(value = "month") String month,
            @RequestParam(value = "company") String company) {
        System.out.println("Company endpoint called with month: " + month + ", company: " + company);
        return chatClient.prompt()
                .tools(profitTool, companyTool)
                .user(u -> u.text("Please give me company profit of {month} from {company}").param("month", month)
                        .param("company", company))
                .call()
                .content();
    }

    @GetMapping("/currencyconverter")
    public String currencyconverter(@RequestParam(value = "month") String month,
            @RequestParam(value = "company") String company, @RequestParam(value = "currency") String currency) {
        System.out.println(
                "Currency converter called with month: " + month + ", company: " + company + ", currency: " + currency);
        return chatClient.prompt()
                .tools(profitTool, convertTool, companyTool)
                .user(u -> u.text(
                        "Please give me company profit of {month} from {company} and convert from dollar to {currency}")
                        .param("month", month).param("company", company).param("currency", currency))
                .call()
                .content();
    }
}
