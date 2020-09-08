package com.ome_r.paypalclient.client;

import com.ome_r.paypalclient.requests.GetTransactionDetailsRequest;
import com.ome_r.paypalclient.requests.TransactionSearchRequest;
import com.ome_r.paypalclient.response.GetTransactionDetailResponse;
import com.ome_r.paypalclient.response.TransactionSearchResponse;
import com.ome_r.paypalclient.utils.Validator;

/**
 * The instance of the client.
 */
public final class PaypalClient {

    private final ClientType clientType;
    private final String username, password, signature;

    /**
     * The API credentials can be found in the following link:
     * https://www.paypal.com/businessprofile/mytools/apiaccess/firstparty/
     * @param clientType The type of the client.
     * @param username The API's username
     * @param password The API's password
     * @param signature The API's signature.
     */
    public PaypalClient(ClientType clientType, String username, String password, String signature){
        Validator.nonNull(clientType, "Client type cannot be null.");
        Validator.nonNull(username, "Username cannot be null.");
        Validator.nonNull(password, "Password cannot be null.");
        Validator.nonNull(signature, "Signature cannot be null.");

        this.clientType = clientType;
        this.username = username;
        this.password = password;
        this.signature = signature;
    }

    /**
     * Get the type of the client.
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * Get the API's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the API's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the API's signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Make a search request for a specific email.
     * @param email The email to lookup.
     * @return A response object that contain a list of all the transaction ids.
     */
    public TransactionSearchResponse searchTransactions(String email){
        TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest(this);
        if(email != null)
            transactionSearchRequest.parameter("EMAIL", email);
        return transactionSearchRequest.query();
    }

    /**
     * Make a lookup request for a specific transaction.
     * @param transactionId The id of the transaction.
     * @return A response object that contain all the information about the transaction.
     */
    public GetTransactionDetailResponse getTransactionDetail(String transactionId){
        GetTransactionDetailsRequest getTransactionDetailsRequest = new GetTransactionDetailsRequest(this);
        if(transactionId != null)
            getTransactionDetailsRequest.parameter("TRANSACTIONID", transactionId);
        return getTransactionDetailsRequest.query();
    }

}
