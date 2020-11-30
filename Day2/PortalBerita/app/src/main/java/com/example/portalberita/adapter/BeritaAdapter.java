package com.example.portalberita.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.portalberita.R;
import com.example.portalberita.model.Berita;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BeritaAdapter extends BaseAdapter {
    Context context;
    private List<Berita> list;

    public BeritaAdapter(Context context, List<Berita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_berita, null);
        }
        Berita berita = list.get(position);
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView categoryTextView =  convertView.findViewById(R.id.categoryTextView);
        ImageView newsImageView = convertView.findViewById(R.id.newsImageView);

        titleTextView.setText(berita.getTitle());
        categoryTextView.setText(berita.getCategory());
        new newsImageUrl(newsImageView).execute(berita.getUrlImage());

        return convertView;
    }

    public class newsImageUrl extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public newsImageUrl(ImageView img){
            this.imageView = img;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            Bitmap bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
