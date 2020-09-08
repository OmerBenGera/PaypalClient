package com.ome_r.paypalclient.utils;

public final class Validator {

    private Validator() { }

    public static void nonNull(Object value, String error){
        check(value != null, error);
    }

    public static void check(boolean check, String error){
        if(!check)
            throw new IllegalArgumentException(error);
    }

}
