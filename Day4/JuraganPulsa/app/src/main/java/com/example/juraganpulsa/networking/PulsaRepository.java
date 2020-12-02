package com.example.juraganpulsa.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.model.PulsaListResponse;
import com.example.juraganpulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PulsaRepository {
    private static PulsaRepository pulsaRepository;

    public static PulsaRepository getInstance(){
        if (pulsaRepository == null){
            pulsaRepository = new PulsaRepository();
        }
        return pulsaRepository;
    }

    private PulsaApi pulsaApi;

    public PulsaRepository(){
        pulsaApi = RetrofitService.cteateService(PulsaApi.class);
    }

    public MutableLiveData<PulsaListResponse> getPulsas(String page, String limit){
        MutableLiveData<PulsaListResponse> pulsaListData = new MutableLiveData<>();
        pulsaApi.getPulsaList(page, limit).enqueue(new Callback<PulsaListResponse>() {
            @Override
            public void onResponse(Call<PulsaListResponse> call, Response<PulsaListResponse> response) {
                if (response.isSuccessful()){
                    Log.v("LogGetPulsa: ",response.body().toString());
                    pulsaListData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PulsaListResponse> call, Throwable t) {
                Log.v("LogErrorFetch: ",t.getMessage());
                pulsaListData.setValue(null);
            }
        });
        return pulsaListData;
    }


    public MutableLiveData<PulsaResponse> postPulsa(Pulsa pulsaPayload){
        MutableLiveData<PulsaResponse> pulsaData = new MutableLiveData<>();
        pulsaApi.postPulsa(pulsaPayload).enqueue(new Callback<PulsaResponse>(){
            @Override
            public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                if (response.isSuccessful()){
                    Log.v("PostData: ",response.body().toString());
                    pulsaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PulsaResponse> call, Throwable t) {
                Log.v("ErrorPostData: ",t.getMessage());
                pulsaData.setValue(null);
            }
        });
     return pulsaData;
    }
}