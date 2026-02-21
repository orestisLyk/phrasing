package com.example.phrasing.core.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }
}
