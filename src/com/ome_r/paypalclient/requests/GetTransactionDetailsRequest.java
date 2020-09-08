package com.ome_r.paypalclient.requests;

import com.ome_r.paypalclient.client.PaypalClient;
import com.ome_r.paypalclient.response.GetTransactionDetailResponse;

public final class GetTransactionDetailsRequest extends Request<GetTransactionDetailResponse> {

    public GetTransactionDetailsRequest(PaypalClient client){
        super(client, client.getClientType().getURL());
        parameter("METHOD", "GetTransactionDetails");
    }

    @Override
    public GetTransactionDetailResponse query() {
        try {
            String response = super.sendRequest();
            return GetTransactionDetailResponse.of(response);
        }catch (Exception ex){
            return GetTransactionDetailResponse.of(ex);
        }
    }

}
