package com.smartpark.exceptions;

public class SceltaNonValidaException extends RuntimeException {
    public SceltaNonValidaException() {
        super("Scelta non valida");
    }
    public SceltaNonValidaException(String message) {
        super(message);
    }
}
