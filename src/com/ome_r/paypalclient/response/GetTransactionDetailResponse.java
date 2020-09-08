package com.ome_r.paypalclient.response;

import com.ome_r.paypalclient.utils.ResponseDeserializer;

import java.util.Map;

/**
 * Represents the response containing all the information about a transaction.
 */
public final class GetTransactionDetailResponse extends Response {

    private final Map<String, String> transactionDetails;

    private GetTransactionDetailResponse(String response, Exception exception){
        super(response, exception);
        transactionDetails = ResponseDeserializer.deserializeTransactionDetail(response);
    }

    public static GetTransactionDetailResponse of(String response){
        return new GetTransactionDetailResponse(response, null);
    }

    public static GetTransactionDetailResponse of(Exception exception){
        return new GetTransactionDetailResponse(null, exception);
    }

    public Map<String, String> getTransactionDetails() {
        return transactionDetails;
    }

}
