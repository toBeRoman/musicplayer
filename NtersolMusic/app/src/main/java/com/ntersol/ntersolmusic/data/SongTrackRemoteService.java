package com.ntersol.ntersolmusic.data;

import com.ntersol.ntersolmusic.domain.SongTrack;

import java.util.List;

public interface SongTrackRemoteService {

    public List<SongTrack> getSongTracks(String platform, String postType) throws Exception;

}
