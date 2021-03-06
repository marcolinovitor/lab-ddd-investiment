package br.com.lab.impacta.investment.api.handler;

import br.com.lab.impacta.investment.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountNotDebitedException;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.exception.NotEnoughBalanceException;
import br.com.lab.impacta.investment.domain.exception.PrivateProductNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MESSAGE_ERROR_DEFAULT = "Não foi possível processar sua requisição!";

    @ExceptionHandler(InvestmentProductNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorProductNotFound(InvestmentProductNotFoundException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(NotEnoughBalanceException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PrivateProductNotAvailableException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(PrivateProductNotAvailableException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountNotDebitedException.class)
    public ResponseEntity<ErrorMessageResponse> errorIsNotDebited(InvestmentAccountNotDebitedException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception) {
        return getErrorMessageResponse(exception.getMessage(), MESSAGE_ERROR_DEFAULT, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message,
                                                                         String description,
                                                                         HttpStatus httpStatus) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                httpStatus.value(),
                new Date(),
                message,
                description);

        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }
}
