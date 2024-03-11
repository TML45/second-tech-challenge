package br.com.fiap.equipe3.secondtechchallenge.controllers.exception;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
