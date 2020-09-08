package com.ome_r.paypalclient.client;

/**
 * The builder class is used to create new instances of PaypalClient with all the required information.
 */
public final class ClientBuilder {

    private ClientType clientType = null;
    private String username = null;
    private String password = null;
    private String signature = null;

    public ClientBuilder(){

    }

    /**
     * Set the client-type. Can be LIVE or SANDBOX.
     * @param clientType The client-type to set.
     */
    public ClientBuilder setClientType(ClientType clientType){
        this.clientType = clientType;
        return this;
    }

    /**
     * Set the API's username of the client.
     * You can find these credentials in the following link:
     * https://www.paypal.com/businessprofile/mytools/apiaccess/firstparty
     * @param username The username to set.
     */
    public ClientBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Set the API's password of the client.
     * You can find these credentials in the following link:
     * https://www.paypal.com/businessprofile/mytools/apiaccess/firstparty
     * @param password The password to set.
     */
    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Set the API's signature of the client.
     * You can find these credentials in the following link:
     * https://www.paypal.com/businessprofile/mytools/apiaccess/firstparty
     * @param signature The signature to set.
     */
    public ClientBuilder setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * Build the client with the information that you provided.
     * Lacking information will result NPE to be thrown.
     */
    public PaypalClient buildClient(){
        return new PaypalClient(clientType, username, password, signature);
    }

}
