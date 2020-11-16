package com.ntersol.ntersolmusic.ui;

import com.ntersol.ntersolmusic.domain.SongTrack;

import java.util.List;

public interface SongListContract {

    public interface View {
        void showLoading();
        void showNoMoreSongs();
        void showSongTracks(List<SongTrack> songTracks);
        void popupUnderMaintenanceDialog();
        void popupErrorDialog(String error);
        void notifyNetworkError();
    }

    public interface Presenter {
        void takeView(View view);
        void onViewResumed();
    }

}
