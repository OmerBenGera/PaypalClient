package com.ome_r.paypalclient.client;

import com.ome_r.paypalclient.requests.GetTransactionDetailsRequest;
import com.ome_r.paypalclient.requests.TransactionSearchRequest;
import com.ome_r.paypalclient.response.GetTransactionDetailResponse;
import com.ome_r.paypalclient.response.TransactionSearchResponse;
import com.ome_r.paypalclient.utils.Validator;

public final class PaypalClient {

    private final ClientType clientType;
    private final String username, password, signature;

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

    public ClientType getClientType() {
        return clientType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSignature() {
        return signature;
    }

    public TransactionSearchResponse searchTransactions(String email){
        TransactionSearchRequest transactionSearchRequest = new TransactionSearchRequest(this);
        if(email != null)
            transactionSearchRequest.parameter("EMAIL", email);
        return transactionSearchRequest.query();
    }

    public GetTransactionDetailResponse getTransactionDetail(String transactionId){
        GetTransactionDetailsRequest getTransactionDetailsRequest = new GetTransactionDetailsRequest(this);
        if(transactionId != null)
            getTransactionDetailsRequest.parameter("TRANSACTIONID", transactionId);
        return getTransactionDetailsRequest.query();
    }

}
