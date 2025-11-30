package com.primosjoyeria.backend.dto;

public class MetalsDevResponse {

    private String status;
    private String timestamp;
    private String currency;
    private String unit;
    private String metal;
    private Rate rate;

    public String getStatus() { return status; }
    public String getTimestamp() { return timestamp; }
    public String getCurrency() { return currency; }
    public String getUnit() { return unit; }
    public String getMetal() { return metal; }
    public Rate getRate() { return rate; }

    public static class Rate {
        private double price;

        public double getPrice() { return price; }
    }
}