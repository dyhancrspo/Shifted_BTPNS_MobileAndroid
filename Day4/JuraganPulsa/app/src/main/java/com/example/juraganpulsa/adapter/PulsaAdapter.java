package com.example.juraganpulsa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.juraganpulsa.MainActivity;
import com.example.juraganpulsa.R;
import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import java.util.ArrayList;

public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.PulsaViewHolder> {

    Context context;
    ArrayList<Pulsa> pulsa;
    RelativeLayout checkoutRl;
    EditText nomorHpEditText;
    TextView pulsaPayTv,paymentTv;

    public PulsaAdapter(Context context, ArrayList<Pulsa> pulsa, RelativeLayout checkoutRl, EditText nomorHpEditText) {
        this.context = context;
        this.pulsa = pulsa;
        this.checkoutRl = checkoutRl;
        this.nomorHpEditText = nomorHpEditText;
    }


    @NonNull
    @Override
    public PulsaAdapter.PulsaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulsa, parent, false);
        return new  PulsaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulsaAdapter.PulsaViewHolder  holder, final int position) {
        holder.nominalTv.setText(String.valueOf(pulsa.get(position).getNominal()));
        holder.priceTv.setText(String.valueOf(pulsa.get(position).getPrice()));


        holder.pulsaLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),pulsa.get(position).getNominal().toString(),Toast.LENGTH_SHORT).show();
//                Util.expand(checkoutRl,2);
//                checkoutRl.setVisibility(View.VISIBLE);


                Intent intent =
                        new Intent( context, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "edit");
                bundle.putString("id", pulsa.get(position).getId().toString());
                bundle.putString("code", pulsa.get(position).getCode());
                bundle.putString("price", pulsa.get(position).getPrice().toString());
                bundle.putString("nominal", pulsa.get(position).getNominal().toString());
                bundle.putString("phone", String.valueOf(nomorHpEditText));
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return pulsa.size();
    }


    public class PulsaViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nominalTextView) TextView nominalTv;
        @BindView(R.id.priceTextView)  TextView priceTv;
        @BindView(R.id.pulsaLl) LinearLayout pulsaLl;

        public PulsaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

