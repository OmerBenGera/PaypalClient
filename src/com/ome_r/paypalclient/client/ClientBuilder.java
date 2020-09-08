package com.ome_r.paypalclient.client;

public final class ClientBuilder {

    private ClientType clientType = null;
    private String username = null;
    private String password = null;
    private String signature = null;

    public ClientBuilder(){

    }

    public ClientBuilder setClientType(ClientType clientType){
        this.clientType = clientType;
        return this;
    }

    public ClientBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public PaypalClient buildClient(){
        return new PaypalClient(clientType, username, password, signature);
    }

}
