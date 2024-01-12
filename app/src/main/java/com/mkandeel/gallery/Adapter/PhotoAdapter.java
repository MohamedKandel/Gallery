package com.mkandeel.gallery.Adapter;

import static com.mkandeel.gallery.Helper.Constants.IMAGE_DATA;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mkandeel.gallery.Helper.ClickListener;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.data.Photos;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photos> list;
    private ClickListener listener;
    private Context context;

    public PhotoAdapter(List<Photos> list, ClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        Photos photo = list.get(position);

        holder.txt_name.setText(photo.getTitle());

        Glide.with(context)
                .load(photo.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_thumb);
            txt_name = itemView.findViewById(R.id.txt_title);

            itemView.setOnClickListener(view -> {
                Bundle extras = new Bundle();
                extras.putParcelable(IMAGE_DATA, list.get(getAdapterPosition()));
                listener.onItemClickListener(getAdapterPosition(), extras);
            });

            itemView.setOnLongClickListener(view -> {
                listener.onItemLongClickListener(getAdapterPosition(), null);
                return true;
            });
        }
    }
}
