package com.example.juraganpulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juraganpulsa.adapter.PulsaAdapter;
import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.viewmodels.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pulsa> pulsaArrayList = new ArrayList<>();
    PulsaAdapter pulsaAdapter;
    RelativeLayout checkourRl;
    EditText nomorHpEditText;
    RecyclerView rvPulsa;
    PulsaViewModel pulsaViewModel;
    TextView refreshTextView, addTextView;
    List<Pulsa> pulsaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById(){
        rvPulsa = findViewById(R.id.pulsaRecyclerView);
        refreshTextView = (TextView) findViewById(R.id.refreshTextView);
        addTextView = (TextView) findViewById(R.id.addTextView);
    }

    private void initData() {
        if (pulsaAdapter == null) {
            pulsaAdapter = new PulsaAdapter(MainActivity.this, pulsaArrayList, checkourRl, nomorHpEditText);
            rvPulsa.setLayoutManager(new GridLayoutManager(this,2));
            rvPulsa.setAdapter(pulsaAdapter);
            rvPulsa.setItemAnimator(new DefaultItemAnimator());
            rvPulsa.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);

        pulsaViewModel.init();
        pulsaViewModel.getPulsaRepository().observe(this, pulsaListResponse -> {
            pulsaList = pulsaListResponse.getData();
            pulsaArrayList.clear();
            pulsaArrayList.addAll(pulsaList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }


    private void getListPulsa(String page, String limit ){
        pulsaViewModel.refresh(page,limit);
        pulsaViewModel.getPulsaRepository().observe(this, pulsaListResponse -> {
            pulsaList = pulsaListResponse.getData();
            pulsaArrayList.clear();
            pulsaArrayList.addAll(pulsaList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }
    void onClickGroup(){
        refreshTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListPulsa("1","20");
            }
        });
        addTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), AddPulsa.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListPulsa("1","20");
    }
}