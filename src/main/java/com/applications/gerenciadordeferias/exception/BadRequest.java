package com.applications.gerenciadordeferias.exception;

public class BadRequest extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequest(String message) {
        super(message);
    }

}
