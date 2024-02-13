package com.example.practica2.entidades;

public class Headers {
    private String keyHeader;
    private String valueHeader;

    public Headers(String keyHeader, String valueHeader) {
        this.keyHeader = keyHeader;
        this.valueHeader = valueHeader;
    }

    public String getKeyHeader() {
        return keyHeader;
    }

    public void setKeyHeader(String keyHeader) {
        this.keyHeader = keyHeader;
    }

    public String getValueHeader() {
        return valueHeader;
    }

    public void setValueHeader(String valueHeader) {
        this.valueHeader = valueHeader;
    }
}
