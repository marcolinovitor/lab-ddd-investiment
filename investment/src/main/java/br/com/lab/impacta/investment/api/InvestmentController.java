package br.com.lab.impacta.investment.api;

import br.com.lab.impacta.investment.application.InvestmentApplication;
import br.com.lab.impacta.investment.application.dto.request.InvestmentRequest;
import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class InvestmentController {
    @Autowired
    private InvestmentApplication investmentApplication;

    @PostMapping("/{accountId}/investment")
    public ResponseEntity<InvestmentResponse> invest(
            @PathVariable Long accountId,
            @RequestBody InvestmentRequest request
    ) {
        InvestmentResponse response = investmentApplication.invest(accountId, request);

        return ResponseEntity.ok(response);
    }
}
