package com.metamorf.eform.interfaces.workflow.exceptions;

public class UndefinedVariableException extends Exception {
    private static final long serialVersionUID = 351638258234565228L;

    public UndefinedVariableException(String key) {
        super(key);
    }

}
