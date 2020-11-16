package com.ntersol.ntersolmusic.domain.usecases;


import com.ntersol.ntersolmusic.domain.exceptions.NtersolException;

public abstract class UseCase<Params, Response> {

    protected abstract Response run(Params params) throws Exception;

    protected NtersolException handleException(Params params, NtersolException e) {
        return e;
    }

    public Response execute(Params params) throws Exception {
        try {
            return run(params);
        } catch (NtersolException e) {
            throw handleException(params, e);
        }
    }

}
