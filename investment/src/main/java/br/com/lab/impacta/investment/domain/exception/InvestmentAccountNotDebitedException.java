package br.com.lab.impacta.investment.domain.exception;

public class InvestmentAccountNotDebitedException extends RuntimeException {
    private String description;
    private String message;

    public String getDescription() {
        return description;
    }

    public InvestmentAccountNotDebitedException() {
        super();
    }

    public InvestmentAccountNotDebitedException(String description, String message) {
        super(message);
        this.description = description;
    }
}
