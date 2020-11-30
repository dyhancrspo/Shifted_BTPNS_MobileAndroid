package com.example.portalberita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.portalberita.model.Berita;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AddBerita extends AppCompatActivity {
    ArrayList<Berita> listBerita = new ArrayList<>();
    EditText titleEditText, categoryEditText, newsImageUrlEditText;
    Button submitButton;
    String json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_berita);
        findViewById();
        onClickGroup();
    }

    void findViewById() {
        titleEditText = findViewById(R.id.titleEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        newsImageUrlEditText = findViewById(R.id.newsImageUrlEditText);
        submitButton = findViewById(R.id.submitButton);
    }


    private void saveData(){
        String title = titleEditText.getText().toString();
        String category = categoryEditText.getText().toString();
        String imageUrl = newsImageUrlEditText.getText().toString();

        Berita berita = new Berita();
        berita.setTitle(title);
        berita.setCategory(category);
        berita.setUrlImage(imageUrl);
        Gson gson = new Gson();
        json = gson.toJson(berita);

    }

    void onClickGroup(){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(listBerita);
                Log.v("Isi Array: ",jsonArray.toString());
                Intent i = getIntent();
                i.putExtra("data", json);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

}