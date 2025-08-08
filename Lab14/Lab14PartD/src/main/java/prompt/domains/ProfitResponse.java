package prompt.domains;

import java.util.List;

public record ProfitResponse(
        ProfitRequest request,
        String message,
        List<Profit> profits) {
}
