package com.example.ournews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ournews.R;
import com.example.ournews.AddBerita;
import com.example.ournews.model.Berita;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context context;
    ArrayList<Berita> berita;

    public BeritaAdapter(Context context, ArrayList<Berita> berita) {
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new  BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder  holder, final int position) {
        holder.titleTv.setText(berita.get(position).getTitle());
        holder.categoryTv.setText(berita.get(position).getCategory());
        Picasso.get().load(berita.get(position).getUrl()).into(holder.urlIv);
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView categoryTv;
        ImageView urlIv;
        LinearLayout beritaLl;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTextView);
            categoryTv = itemView.findViewById(R.id.categoryTextView);
            urlIv = itemView.findViewById(R.id.beritaImageView);
            beritaLl = itemView.findViewById(R.id.beritaLl);

        }
    }

}

