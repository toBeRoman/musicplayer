package com.ntersol.ntersolmusic.data;

import com.ntersol.ntersolmusic.domain.SongTrack;

import java.util.ArrayList;
import java.util.List;

public class SongTrackMapper {

    public static List<SongTrack> toDomain(List<SongTrackRaw> songTracksRawList) {
        ArrayList<SongTrack> songTracks = new ArrayList<>();
        for(SongTrackRaw songTrackRaw : songTracksRawList) {
            songTracks.add(toDomain(songTrackRaw));
        }
        return songTracks;

    }

    public static SongTrack toDomain(SongTrackRaw songTrackRaw) {
        return new SongTrack(
                songTrackRaw.getName(), songTrackRaw.getProfileImageUrl(), songTrackRaw.getNormalizedProfileImageUrl(), songTrackRaw.getMerchantName()
        );
    }



}
