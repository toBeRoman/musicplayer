package com.ntersol.ntersolmusic.data;

import com.ntersol.ntersolmusic.domain.SongTrack;
import com.ntersol.ntersolmusic.domain.SongTrackGateway;

import java.util.List;

public class SongTrackRepository implements SongTrackGateway {


    private final SongTrackRemoteService songTrackRemoteService;

    public SongTrackRepository(SongTrackRemoteService songTrackRemoteService) {
        this.songTrackRemoteService = songTrackRemoteService;
    }

    @Override
    public List<SongTrack> fetchSongTracks(String platform, String postType) throws Exception {
        return songTrackRemoteService.getSongTracks(platform, postType);
    }
}
