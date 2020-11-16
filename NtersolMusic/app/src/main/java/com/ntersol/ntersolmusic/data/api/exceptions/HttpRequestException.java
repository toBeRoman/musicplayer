package com.ntersol.ntersolmusic.data.api.exceptions;

import android.support.annotation.Nullable;

import com.ntersol.ntersolmusic.domain.exceptions.NtersolException;

public class HttpRequestException extends NtersolException {

    private int code;
    private String message;

    public HttpRequestException(String message, int code) {
        super("HttpRequestException happened with message=`"+message+"` of code=`"+code+"`");
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
