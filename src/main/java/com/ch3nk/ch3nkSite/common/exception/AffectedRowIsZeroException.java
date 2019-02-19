package com.ch3nk.ch3nkSite.common.exception;

public class AffectedRowIsZeroException extends RuntimeException{
    public AffectedRowIsZeroException() {
        super();
    }

    public AffectedRowIsZeroException(String message) {
        super(message);
    }
}
