package com.ntersol.ntersolmusic.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.ntersol.ntersolmusic.R;
import com.ntersol.ntersolmusic.UseCasesProvider;
import com.ntersol.ntersolmusic.adapter.SongsAdapter;
import com.ntersol.ntersolmusic.domain.SongTrack;

import java.util.List;

public class SongListActivity extends AppCompatActivity implements SongListContract.View, SongsAdapter.OnItemClickListener {

    private SongListContract.Presenter presenter = null;

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UseCasesProvider.initialize(SongListActivity.this);
        presenter = new SongListPresenter(
                UseCasesProvider.provideSongTrackUseCase()) {
        };
        presenter.takeView(this);

        mRecyclerView = findViewById(R.id.songs_list_rv_main);
        mLayoutManager = new LinearLayoutManager(SongListActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new SongsAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResumed();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNoMoreSongs() {
        Toast.makeText(SongListActivity.this, "Song tracks unavailable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSongTracks(List<SongTrack> songTracks) {
        if (songTracks.isEmpty()) {
            Toast.makeText(SongListActivity.this, "Song tracks unavailable", Toast.LENGTH_SHORT).show();
        } else {
            ((SongsAdapter) mRecyclerView.getAdapter()).setSongTracks(songTracks);
        }
    }

    @Override
    public void popupUnderMaintenanceDialog() {

    }

    @Override
    public void popupErrorDialog(String error) {

    }

    @Override
    public void notifyNetworkError() {

    }

    @Override
    public void onItemClick(SongTrack songTrack) {
        Toast.makeText(SongListActivity.this, songTrack.getName() + " CLICKED", Toast.LENGTH_SHORT).show();
    }
}