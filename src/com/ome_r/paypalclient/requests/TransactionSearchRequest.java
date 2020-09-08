package com.ome_r.paypalclient.requests;

import com.ome_r.paypalclient.client.PaypalClient;
import com.ome_r.paypalclient.response.TransactionSearchResponse;

/**
 * Represents the request of making a search of all transactions for a specific email.
 */
public final class TransactionSearchRequest extends Request<TransactionSearchResponse> {

    public TransactionSearchRequest(PaypalClient client){
        super(client, client.getClientType().getURL());
        parameter("METHOD", "TransactionSearch").parameter("STARTDATE", "2013-08-24T05:38:48Z");
    }

    @Override
    public TransactionSearchResponse query() {
        try {
            String response = super.sendRequest();
            return TransactionSearchResponse.of(response);
        }catch (Exception ex){
            return TransactionSearchResponse.of(ex);
        }
    }

}
