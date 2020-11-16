package com.ntersol.ntersolmusic.domain;

import java.util.List;

public interface SongTrackGateway {

    public List<SongTrack> fetchSongTracks(String platform, String postType) throws Exception;


}
