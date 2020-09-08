package com.ome_r.paypalclient.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public final class RequestsUtils {

    private RequestsUtils() { }

    public static String sendRequest(String _url, String _parameters) throws IOException {        Validator.nonNull(_url, "URL cannot be null.");
        URL url = new URL(_url);

        HttpsURLConnection connection = null;

        try {
            connection = (HttpsURLConnection) url.openConnection();

            // Setting the request method.
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            try (DataOutputStream output = new DataOutputStream(connection.getOutputStream())) {
                output.writeBytes(_parameters);
            }

            StringBuilder responseRaw = new StringBuilder();
            InputStream inputStream = connection.getErrorStream() != null ? connection.getErrorStream() : connection.getInputStream();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseRaw.append(line);
                }
            }

            return responseRaw.toString();
        }

        finally {
            if(connection != null)
                connection.disconnect();
        }
    }

}
