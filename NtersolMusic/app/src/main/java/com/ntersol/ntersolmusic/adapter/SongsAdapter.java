package com.ntersol.ntersolmusic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntersol.ntersolmusic.R;
import com.ntersol.ntersolmusic.domain.SongTrack;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {


    public interface OnItemClickListener {
        public void onItemClick(SongTrack songTrack);
    }

    private List<SongTrack> songTracks;

    public SongsAdapter(OnItemClickListener listener) {

        this.listener = listener;
        this.songTracks = new ArrayList<SongTrack>();
    }

    private OnItemClickListener listener;

    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songs_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {

        final SongTrack songTrack = songTracks.get(position);

        holder.tvSongTitle.setText(songTrack.getName());
        holder.tvArtist.setText(songTrack.getMerchantName());

        Context context = holder.ivThumbnail.getContext();
        if (holder.ivThumbnail != null) {
            if (songTrack.getProfileImageUrl() != null) {
                if (context != null && songTrack.getProfileImageUrl().contains("http")) {
                    Picasso.with(context).load(songTrack.getProfileImageUrl()).placeholder(R.drawable.thumbnail_placeholder).error(R.drawable.thumbnail_placeholder).into(holder.ivThumbnail);
                } else {
                    holder.ivThumbnail.setImageResource(R.drawable.thumbnail_placeholder);
                }

            } else {
                holder.ivThumbnail.setImageResource(R.drawable.thumbnail_placeholder);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(songTrack);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_songs_list;
    }

    public void setSongTracks(List<SongTrack> songTracks) {
        this.songTracks = songTracks;
        notifyDataSetChanged();
    }

    public void addSongTracks(List<SongTrack> songTracks) {
        this.songTracks.addAll(songTracks);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return songTracks.size();
    }

}