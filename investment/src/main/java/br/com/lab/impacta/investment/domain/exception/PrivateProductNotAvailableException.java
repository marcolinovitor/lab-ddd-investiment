package br.com.lab.impacta.investment.domain.exception;

public class PrivateProductNotAvailableException extends RuntimeException {
    private String description;
    private String message;

    public String getDescription() {
        return description;
    }

    public PrivateProductNotAvailableException() {
        super();
    }

    public PrivateProductNotAvailableException(String description, String message) {
        super(message);
        this.description = description;
    }
}
