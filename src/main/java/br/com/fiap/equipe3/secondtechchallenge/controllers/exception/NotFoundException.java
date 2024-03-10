package br.com.fiap.equipe3.secondtechchallenge.controllers.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
