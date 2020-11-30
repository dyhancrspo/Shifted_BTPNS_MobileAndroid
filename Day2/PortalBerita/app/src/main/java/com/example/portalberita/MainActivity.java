package com.example.portalberita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.portalberita.adapter.BeritaAdapter;
import com.example.portalberita.model.Berita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_MAIN_ACTIVITY = 007 ;
    ListView beritaListView;
    BeritaAdapter beritaAdapter;
    FloatingActionButton addButton;
    ArrayList<Berita> listBerita = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
        fakeDataListView();
    }

    void findViewById() {
        beritaListView = findViewById(R.id.beritaListView);
        addButton = findViewById(R.id.addButton);
    }

    void initData(){
        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parse(data.getStringExtra("data"));
    }

    private void parse( String data){
        Gson gson = new Gson();
        Berita berita = gson.fromJson(data,Berita.class);
        Log.v("Berita : ", berita.getTitle());
        listBerita.add(berita);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBerita.class);
                intent.putExtra("id", "CEK");
                startActivityForResult(intent, CODE_MAIN_ACTIVITY);
            }
        });
    }


    void fakeDataListView(){
        Berita berita = new Berita();
        berita.setTitle("\"God Hand\" Maradona meninggal dunia");
        berita.setCategory("sport");
        berita.setUrlImage("https://img.fifa.com/image/upload/t_l1/mpw6tecx6rsinaivhmty.jpg");
        listBerita.add(berita);
        beritaAdapter.notifyDataSetChanged();
    }

}