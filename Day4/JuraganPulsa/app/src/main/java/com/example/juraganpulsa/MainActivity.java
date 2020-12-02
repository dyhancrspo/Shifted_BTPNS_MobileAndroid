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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juraganpulsa.adapter.PulsaAdapter;
import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.model.PulsaBuyer;
import com.example.juraganpulsa.viewmodels.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pulsa> pulsaArrayList = new ArrayList<>();
    PulsaAdapter pulsaAdapter;
    RelativeLayout checkoutRl;
    LinearLayout btn_pay;
    TextView pulsaPayTv, paymentTv;
    ImageView ic_close;
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

        initData();
    }

    void findViewById(){
        rvPulsa = findViewById(R.id.pulsaRecyclerView);
        pulsaPayTv = (TextView) findViewById(R.id.pulsaPayTv);
        paymentTv = (TextView) findViewById(R.id.paymentTv);
        ic_close = (ImageView) findViewById(R.id.ic_close);
        checkoutRl = (RelativeLayout) findViewById(R.id.checkoutRl);
        btn_pay = (LinearLayout) findViewById(R.id.btn_pay);
    }

    private void initData() {
        if (pulsaAdapter == null) {
            pulsaAdapter = new PulsaAdapter(MainActivity.this, pulsaArrayList, checkoutRl, nomorHpEditText, pulsaPayTv, paymentTv, ic_close, btn_pay);
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


}