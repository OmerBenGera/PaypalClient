package com.ome_r.paypalclient.response;

import com.ome_r.paypalclient.utils.ResponseDeserializer;

import java.util.List;

/**
 * Represents the response containing all the transaction ids of an email.
 */
public final class TransactionSearchResponse extends Response {

    private final List<String> transactionIdList;

    private TransactionSearchResponse(String response, Exception exception){
        super(response, exception);
        this.transactionIdList = ResponseDeserializer.deserializeSearchTransaction(response);
    }

    public static TransactionSearchResponse of(String response){
        return new TransactionSearchResponse(response, null);
    }

    public static TransactionSearchResponse of(Exception exception){
        return new TransactionSearchResponse(null, exception);
    }

    public List<String> getTransactionIds() {
        checkFailure();
        return transactionIdList;
    }

}
