package com.example.demo.models;

public enum EditionType {
    NORMAL("NORMAL"),
    PROMO("PROMO"),
    GOLD("GOLD");

    private String value;
    EditionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
