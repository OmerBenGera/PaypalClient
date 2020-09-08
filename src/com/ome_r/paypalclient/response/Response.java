package com.ome_r.paypalclient.response;

import com.ome_r.paypalclient.utils.Validator;

public abstract class Response {

    private final String response;
    private final Exception exception;

    protected boolean silenceFailure = false;

    protected Response(String response, Exception exception){
        this.response = response;
        this.exception = exception;
    }

    public void setSafe(){
        this.silenceFailure = true;
    }

    public boolean hasFailed(){
        return exception != null;
    }

    public Exception getFailureReason(){
        return exception;
    }

    public String getResponse(){
        return response;
    }

    protected void checkFailure(){
        Validator.check(silenceFailure || !hasFailed(), "Cannot get data from a failed unsafe response.");
    }

}
