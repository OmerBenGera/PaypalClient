package com.ome_r.paypalclient.utils;

public final class Validator {

    private Validator() { }

    /**
     * Making sure a variable is not null.
     * @param value The variable to check.
     * @param error The error to throw if the variable is null.
     */
    public static void nonNull(Object value, String error){
        check(value != null, error);
    }

    /**
     * Making sure a condition is true.
     * @param check The condition to check.
     * @param error The error to throw if the condition is false.
     */
    public static void check(boolean check, String error){
        if(!check)
            throw new IllegalArgumentException(error);
    }

}
