package com.example.demo.models;

public enum AgeRestriction {
    MINOR("MINOR"),
    TEEN("TEEN"),
    ADULT("ADULT");

    private String value;

    AgeRestriction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
