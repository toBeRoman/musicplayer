package com.ntersol.ntersolmusic.data.api.exceptions;

import com.ntersol.ntersolmusic.domain.exceptions.NtersolException;

import java.io.IOException;

public class NetworkException extends NtersolException {

    private IOException exception;

    public NetworkException(IOException exception) {
        super("NetworkException happened of class=`"+exception.getClass().getSimpleName()+"` with message "+exception.getMessage());
        this.exception = exception;
    }

    public IOException getException() {
        return exception;
    }
}
