package com.hereforbeer.web;

public class BadRequestException extends RuntimeException {
    private ErrorInfo info;

    public BadRequestException(ErrorInfo info) {
        this.info = info;
    }

    public ErrorInfo getInfo() {
        return info;
    }
}
