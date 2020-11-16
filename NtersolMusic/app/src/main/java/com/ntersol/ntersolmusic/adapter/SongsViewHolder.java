package com.ntersol.ntersolmusic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntersol.ntersolmusic.R;

public class SongsViewHolder extends RecyclerView.ViewHolder {

    ImageView ivThumbnail;
    TextView tvSongTitle;
    TextView tvArtist;

    public SongsViewHolder(View itemView) {
        super(itemView);

        ivThumbnail = itemView.findViewById(R.id.song_list_iv_thumbnail);
        tvSongTitle = itemView.findViewById(R.id.songs_list_tv_title);
        tvArtist = itemView.findViewById(R.id.songs_list_tv_artist);
    }

}
