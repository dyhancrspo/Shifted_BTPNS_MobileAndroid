package com.example.ournews.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.ournews.model.Berita;
import com.example.ournews.model.BeritaListResponse;
import com.example.ournews.model.BeritaResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class BeritaRepository {
    private static BeritaRepository beritaRepository;

    public static BeritaRepository getInstance(){
        if (beritaRepository == null){
            beritaRepository = new BeritaRepository();
        }
        return beritaRepository;
    }

    private BeritaApi beritaApi;

    public BeritaRepository(){
        beritaApi = RetrofitService.cteateService(BeritaApi.class);
    }

    public MutableLiveData<BeritaListResponse> getBeritas(String page, String limit){
        MutableLiveData<BeritaListResponse> beritaListData = new MutableLiveData<>();
        beritaApi.getBeritaList(page, limit).enqueue(new Callback<BeritaListResponse>() {
            @Override
            public void onResponse(Call<BeritaListResponse> call, Response<BeritaListResponse> response) {
                if (response.isSuccessful()){
                    beritaListData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaListResponse> call, Throwable t) {
                beritaListData.setValue(null);
            }
        });
        return beritaListData;
    }


    public MutableLiveData<BeritaResponse> postBerita(Berita beritaPayload){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.postBerita(beritaPayload).enqueue(new Callback<BeritaResponse>(){
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    beritaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                beritaData.setValue(null);
            }
        });
     return beritaData;
    }
}