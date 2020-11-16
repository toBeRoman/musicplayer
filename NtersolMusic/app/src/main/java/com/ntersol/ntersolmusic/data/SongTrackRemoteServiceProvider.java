package com.ntersol.ntersolmusic.data;

import com.ntersol.ntersolmusic.data.api.RemoteServiceHelper;
import com.ntersol.ntersolmusic.data.api.SongsEndpoints;
import com.ntersol.ntersolmusic.domain.SongTrack;

import java.util.List;

import retrofit2.Retrofit;

public class SongTrackRemoteServiceProvider extends RemoteServiceHelper implements SongTrackRemoteService {

    private final Retrofit retrofit;
    private final SongsEndpoints songsEndpoint;

    public SongTrackRemoteServiceProvider(Retrofit retrofit) {
        this.retrofit = retrofit;
        songsEndpoint = retrofit.create(SongsEndpoints.class);
    }

    @Override
    public List<SongTrack> getSongTracks(String platform, String postType) throws Exception {
        List<SongTrackRaw> songTracksRawList = executeRequest(
                songsEndpoint.getSongTracks(platform, postType)
        );

        return SongTrackMapper.toDomain(songTracksRawList);
    }
}
