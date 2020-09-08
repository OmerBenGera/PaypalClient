package com.ome_r.paypalclient.response;

import com.ome_r.paypalclient.utils.Validator;

/**
 * Abstract class to handle responses easily.
 */
public abstract class Response {

    private final String response;
    private final Exception exception;

    protected boolean silenceFailure = false;

    /**
     * @param response The raw response that was received from the API. May be null if failed.
     * @param exception The exception that was thrown when retrieving response. May be null if succeed.
     */
    protected Response(String response, Exception exception){
        this.response = response;
        this.exception = exception;
    }

    /**
     * Setting the response data safe to be used.
     * When enabled, you'll be able to retrieve the data (null) even when failed.
     */
    public void setSafe(){
        this.silenceFailure = true;
    }

    /**
     * Check whether or not the request has failed.
     */
    public boolean hasFailed(){
        return exception != null;
    }

    /**
     * Get the reason for the failure, if exists.
     */
    public Exception getFailureReason(){
        return exception;
    }

    /**
     * Get the raw response that was retrieved from the API.
     */
    public String getResponse(){
        return response;
    }

    protected void checkFailure(){
        Validator.check(silenceFailure || !hasFailed(), "Cannot get data from a failed unsafe response.");
    }

}
