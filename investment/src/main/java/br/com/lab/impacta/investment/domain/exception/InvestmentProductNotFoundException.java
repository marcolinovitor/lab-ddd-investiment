package br.com.lab.impacta.investment.domain.exception;

public class InvestmentProductNotFoundException extends RuntimeException {

    private String description;
    private String message;

    public String getDescription() {
        return description;
    }

    public InvestmentProductNotFoundException() {
        super();
    }

    public InvestmentProductNotFoundException(String description, String message) {
        super(message);
        this.description = description;
    }
}
