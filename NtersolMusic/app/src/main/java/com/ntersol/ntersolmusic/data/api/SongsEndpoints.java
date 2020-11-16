package com.ntersol.ntersolmusic.data.api;


import com.ntersol.ntersolmusic.data.SongTrackRaw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SongsEndpoints {

    @GET("/api/v1/public/posts/search")
    Call<List<SongTrackRaw>> getSongTracks(
            @Query("platform") String country,
            @Query("postType") String postType
    );


}