package com.atul.musicplayerlite.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atul.musicplayerlite.R;
import com.atul.musicplayerlite.listener.AlbumSelectListener;
import com.atul.musicplayerlite.model.Album;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    public final List<Album> albumList;
    public final AlbumSelectListener listener;

    public AlbumsAdapter(List<Album> albums, AlbumSelectListener listener) {
        this.albumList = albums;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.albumName.setText(albumList.get(position).title);
        holder.albumDetails.setText(String.format(Locale.getDefault(), "%s • %s • %d songs",
                albumList.get(position).music.get(0).artist,
                albumList.get(position).year,
                albumList.get(position).music.size()));

        Glide.with(holder.albumArt.getContext())
                .load(albumList.get(position).music.get(0).albumArt)
                .placeholder(R.drawable.ic_album_art)
                .into(holder.albumArt);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView albumDetails;
        private final TextView albumName;
        private final ImageView albumArt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            albumArt = itemView.findViewById(R.id.albumArt);
            albumDetails = itemView.findViewById(R.id.album_details);
            albumName = itemView.findViewById(R.id.album_name);

            itemView.findViewById(R.id.root_layout).setOnClickListener(v ->
                    listener.selectedAlbum(albumList.get(getAdapterPosition())));
        }
    }
}
