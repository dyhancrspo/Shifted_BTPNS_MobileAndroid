package com.example.ournews.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ournews.model.Berita;
import com.example.ournews.model.BeritaListResponse;
import com.example.ournews.model.BeritaResponse;
import com.example.ournews.networking.BeritaRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritaListResponse> mutableLiveData;
    private BeritaRepository beritaRepository;
    private MutableLiveData<BeritaResponse> mutableBeritaLiveData;


    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData = beritaRepository.getBeritas("1", "10");
    }

    public LiveData<BeritaListResponse> getBeritaRepository() {
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritaRepository.getBeritas(page, limit);
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData = beritaRepository.getBeritas("1", "10");
    }


    public LiveData<BeritaResponse> postBeritaRepository(Berita beritaPayload) {
        if (mutableBeritaLiveData == null) {
            beritaRepository = BeritaRepository.getInstance();
        }
        mutableBeritaLiveData = beritaRepository.postBerita(beritaPayload);
        return mutableBeritaLiveData;
    }



}