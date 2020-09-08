package com.ome_r.paypalclient.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResponseDeserializer {

    private ResponseDeserializer() { }

    /**
     * Deserialize the search transactions response into a list of responses.
     * @param response The response from the API for a search request.
     */
    public static List<String> deserializeSearchTransaction(String response){
        List<String> transactionIds = new ArrayList<>();

        if(response == null)
            return transactionIds;

        for(String parameter : response.split("&")){
            if(parameter.contains("L_TRANSACTIONID")){
                transactionIds.add(parameter.split("=")[1]);
            }
        }

        return transactionIds;
    }

    /**
     * Deserialize the details of a transaction response into a map of all the information.
     * @param response The response from the API for a get transaction detail request.
     */
    public static Map<String, String> deserializeTransactionDetail(String response){
        Map<String, String> transactionDetail = new HashMap<>();

        if(response == null)
            return transactionDetail;

        for(String line : response.split("&")){
            String[] sections = line.split("=");
            transactionDetail.put(sections[0], sections[1]);
        }

        return transactionDetail;
    }

}
