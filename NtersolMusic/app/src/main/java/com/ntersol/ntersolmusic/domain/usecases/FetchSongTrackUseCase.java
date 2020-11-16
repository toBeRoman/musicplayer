package com.ntersol.ntersolmusic.domain.usecases;


import com.ntersol.ntersolmusic.data.api.exceptions.HttpRequestException;
import com.ntersol.ntersolmusic.domain.SongTrack;
import com.ntersol.ntersolmusic.domain.SongTrackGateway;
import com.ntersol.ntersolmusic.domain.exceptions.NtersolException;
import com.ntersol.ntersolmusic.domain.exceptions.SongsUnavailableException;

import java.util.List;

public class FetchSongTrackUseCase extends
        UseCase<FetchSongTrackUseCase.Param, FetchSongTrackUseCase.Response> {

    private final SongTrackGateway gateway;

    public FetchSongTrackUseCase(SongTrackGateway gateway) {
        this.gateway = gateway;
    }

    private Response fetchSongTracks(String platform, String postType) throws Exception {
        List<SongTrack> fetchedSongTrack = gateway.fetchSongTracks(platform, postType);
        return new Response(
                fetchedSongTrack
        );
    }

    @Override
    protected Response run(Param param) throws Exception {
        try {
            return fetchSongTracks(param.getPlatform(), param.getPostType());
        } catch (NtersolException e) {
            if (e instanceof HttpRequestException) {
                if (((HttpRequestException) e).getCode() == 401) {
                    throw new SongsUnavailableException();
                }
            }
            throw e;
        }
    }

    public static class Param {
        private String platform;
        private String postType;

        public Param(String platform, String postType) {
            this.platform = platform;
            this.postType = postType;
        }

        public String getPlatform() {
            return platform;
        }

        public String getPostType() {
            return postType;
        }
    }

    public static class Response {

        public final List<SongTrack> songTracks;

        public Response(List<SongTrack> songTracks) {
            this.songTracks = songTracks;
        }

    }

}
