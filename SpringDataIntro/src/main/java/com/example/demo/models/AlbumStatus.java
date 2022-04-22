package com.example.demo.models;

public enum AlbumStatus {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    private String value;

    AlbumStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
