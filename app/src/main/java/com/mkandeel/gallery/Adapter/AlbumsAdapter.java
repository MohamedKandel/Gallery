package com.mkandeel.gallery.Adapter;

import static com.mkandeel.gallery.Helper.Constants.ALBUM_DATA;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkandeel.gallery.Helper.ClickListener;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.data.Albums;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private List<Albums> albums;
    private ClickListener listener;

    public AlbumsAdapter(List<Albums> albums, ClickListener listener) {
        this.albums = albums;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.albums_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.ViewHolder holder, int position) {
        holder.txt_name.setText(albums.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name;
        private ImageButton btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            btn = itemView.findViewById(R.id.btn_go);
            itemView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ALBUM_DATA, albums.get(getAdapterPosition()));
                listener.onItemClickListener(getAdapterPosition(), bundle);
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClickListener(getAdapterPosition(), null);
                    return true;
                }
            });
        }
    }
}
