package com.smartpark.exceptions;

public class CapacitaEsauritaException extends RuntimeException {
    public CapacitaEsauritaException() {
        super("Capacità massima del parcheggio raggiunta");
    }
    public CapacitaEsauritaException(String message) {
        super(message);
    }
}
