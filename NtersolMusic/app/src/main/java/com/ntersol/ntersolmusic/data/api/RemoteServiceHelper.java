package com.ntersol.ntersolmusic.data.api;


import com.ntersol.ntersolmusic.data.api.exceptions.HttpRequestException;
import com.ntersol.ntersolmusic.data.api.exceptions.NetworkException;
import com.ntersol.ntersolmusic.domain.exceptions.UnderMaintenanceException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RemoteServiceHelper {

    protected <T> T executeRequest(Call<T> call) throws Exception {
        Response<T> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new NetworkException(e);
        }

        if(!response.isSuccessful()) {
            if(response.errorBody() != null) {
                if(response.code() == 503)
                    throw new UnderMaintenanceException(response.errorBody().string());
                else
                    throw new HttpRequestException(response.errorBody().string(), response.code());
            }
        }
        return response.body();
    }

}
