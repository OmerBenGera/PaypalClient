package com.ome_r.paypalclient.client;

public enum ClientType {

    LIVE("https://api-3t.paypal.com/nvp"),
    SANDBOX("https://api-3t.sandbox.paypal.com/nvp");

    private final String URL;

    ClientType(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }
}
