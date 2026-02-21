package com.example.phrasing.core.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}
