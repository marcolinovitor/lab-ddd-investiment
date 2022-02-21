package br.com.lab.impacta.investment.domain.exception;

public class NotEnoughBalanceException extends RuntimeException {
    private String description;
    private String message;

    public String getDescription() {
        return description;
    }

    public NotEnoughBalanceException() {
        super();
    }

    public NotEnoughBalanceException(String description, String message) {
        super(message);
        this.description = description;
    }
}
