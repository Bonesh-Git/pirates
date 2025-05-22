package com.bonesh.wallet.exception;

public class AuthenticationFailed extends RuntimeException {
    private int code;
    private String message;

    public AuthenticationFailed(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
