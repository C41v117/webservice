package com.metamorf.eform.interfaces.workflow.exceptions;

public class ProcessNotFoundException extends Exception {
    private static final long serialVersionUID = 2213707630530620779L;

    public ProcessNotFoundException() {
        super();
    }

    public ProcessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessNotFoundException(String message) {
        super(message);
    }

    public ProcessNotFoundException(Throwable cause) {
        super(cause);
    }

}
