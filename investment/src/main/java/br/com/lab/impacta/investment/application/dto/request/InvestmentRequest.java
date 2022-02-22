package br.com.lab.impacta.investment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentRequest {
    private Long productId;
    private Double value;

}
