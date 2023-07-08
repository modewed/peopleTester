package com.example.demo.model;

public enum Role {


    USER("User"),
    ADMIN("Admin"),
    REDACTOR("Redactor");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
