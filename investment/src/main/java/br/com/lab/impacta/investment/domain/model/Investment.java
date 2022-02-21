package br.com.lab.impacta.investment.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long accountId;
    private Double value;
    @CreationTimestamp
    private Date date;
    private boolean privateInvestment;

    public Investment(Long productId, Long accountId, Double value) {
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
    }

    public boolean enoughBalanceForInvestment(Double accountBalance) {
        return this.value < accountBalance;
    }
}
