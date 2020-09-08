package com.ome_r.paypalclient.requests;

import com.ome_r.paypalclient.client.PaypalClient;
import com.ome_r.paypalclient.response.Response;
import com.ome_r.paypalclient.utils.Validator;
import com.ome_r.paypalclient.utils.RequestsUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Request<T extends Response> {

    private final Map<String, String> parameters = new HashMap<>();
    private final String URL;

    protected Request(PaypalClient paypalClient, String URL){
        this.URL = URL;
        parameter("USER", paypalClient.getUsername()).parameter("PWD", paypalClient.getPassword())
                .parameter("SIGNATURE", paypalClient.getSignature()).parameter("VERSION", "94");
    }

    public final Request<T> parameter(String key, String value){
        Validator.nonNull(key, "Key cannot be null.");
        Validator.nonNull(value, "Value cannot be null.");
        this.parameters.put(key, value);
        return this;
    }

    protected final String sendRequest() throws IOException{
        String parameters = buildParameters();
        return RequestsUtils.sendRequest(URL, parameters);
    }

    public abstract T query();

    private String buildParameters(){
        StringBuilder parameters = new StringBuilder();
        this.parameters.forEach((k, v) -> parameters.append("&").append(k).append("=").append(v));
        return parameters.length() == 0 ? "" : parameters.substring(1);
    }

}
