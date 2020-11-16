package com.ntersol.ntersolmusic.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import com.ntersol.ntersolmusic.data.api.exceptions.NetworkException;
import com.ntersol.ntersolmusic.domain.SongTrack;
import com.ntersol.ntersolmusic.domain.exceptions.NoDataAvailableException;
import com.ntersol.ntersolmusic.domain.exceptions.UnderMaintenanceException;
import com.ntersol.ntersolmusic.domain.usecases.FetchSongTrackUseCase;

import java.util.ArrayList;
import java.util.List;

public class SongListPresenter implements SongListContract.Presenter {

    private SongListContract.View view = null;
    private final FetchSongTrackUseCase fetchSongTrackUseCase;

    private List<SongTrack> songTracks = new ArrayList<>();
    private final String DEFAULT_PLATFORM = "RevCastFilms";
    private final String DEFAULT_POST_TYPE = "Audio";

    enum SongListUI {
        SHOW_LOADING,
        SHOW_SONG_TRACKS,
        SHOW_NO_MORE_SONG_TRACKS,
        UNDER_MAINTENANCE,
        GENERIC_DIALOG,
        NETWORK_ERROR
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public synchronized void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (SongListUI.values()[msg.what]) {
                case SHOW_LOADING:
                    if (view != null)
                        view.showLoading();
                    break;
                case SHOW_SONG_TRACKS:
                    if (view != null)
                        view.showSongTracks(songTracks);
                    break;
                case SHOW_NO_MORE_SONG_TRACKS:
                    if (view != null)
                        view.showNoMoreSongs();
                    break;
                case UNDER_MAINTENANCE:
                    if (view != null)
                        view.popupUnderMaintenanceDialog();
                    break;
                case GENERIC_DIALOG:
                    if (view != null)
                        view.popupErrorDialog(msg.getData().getString("error"));
                    break;
                case NETWORK_ERROR:
                    if (view != null)
                        view.notifyNetworkError();
                    break;
            }
        }
    };

    public SongListPresenter(FetchSongTrackUseCase fetchSongTrackUseCase) {
        this.fetchSongTrackUseCase = fetchSongTrackUseCase;
    }

    @Override
    public void takeView(SongListContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewResumed() {
        handler.sendEmptyMessage(SongListUI.SHOW_LOADING.ordinal());
        try {
            loadSongTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadSongTracks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    songTracks = fetchSongTrackUseCase.execute(new FetchSongTrackUseCase.Param(DEFAULT_PLATFORM, DEFAULT_POST_TYPE)).songTracks;
                    if (songTracks.isEmpty()) {
                        handler.sendEmptyMessage(SongListUI.SHOW_NO_MORE_SONG_TRACKS.ordinal());
                    } else {
                        handler.sendEmptyMessage(SongListUI.SHOW_SONG_TRACKS.ordinal());
                    }
                } catch (Exception e) {
                    if (e instanceof UnderMaintenanceException) {
                        handler.sendEmptyMessage(SongListUI.UNDER_MAINTENANCE.ordinal());
                    } else if (e instanceof NetworkException) {
                        showNoSongTrack();
                        handler.sendEmptyMessage(SongListUI.NETWORK_ERROR.ordinal());
                    } else if (e instanceof NoDataAvailableException) {
                        songTracks = new ArrayList<>();
                        handler.sendEmptyMessage(SongListUI.SHOW_SONG_TRACKS.ordinal());
                    } else {
                        Bundle data = new Bundle();
                        data.putString("error", e.toString());

                        Message message = new Message();
                        message.setData(data);
                        message.what = SongListUI.GENERIC_DIALOG.ordinal();
                        handler.sendMessage(message);
                    }
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showNoSongTrack() {
        Bundle data = new Bundle();
        data.putInt("count", 0);

        Message message = new Message();
        message.setData(data);
        message.what = SongListUI.SHOW_SONG_TRACKS.ordinal();
        handler.sendMessage(message);
    }
}
