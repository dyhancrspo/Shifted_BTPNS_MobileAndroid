package com.example.juraganpulsa.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.model.PulsaListResponse;
import com.example.juraganpulsa.model.PulsaResponse;
import com.example.juraganpulsa.networking.PulsaRepository;


public class PulsaViewModel extends ViewModel {
    private MutableLiveData<PulsaListResponse> mutableLiveData;
    private PulsaRepository pulsaRepository;
    private MutableLiveData<PulsaResponse> mutablePulsaLiveData;


    public void init(){
        if (mutableLiveData != null){
            return;
        }
        pulsaRepository = PulsaRepository.getInstance();
        mutableLiveData = pulsaRepository.getPulsas("1", "10");
    }

    public LiveData<PulsaListResponse> getPulsaRepository() {
        return mutableLiveData;
    }

    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = pulsaRepository.getPulsas(page, limit);
            return;
        }
        pulsaRepository = PulsaRepository.getInstance();
        mutableLiveData = pulsaRepository.getPulsas("1", "10");
    }


    public LiveData<PulsaResponse> postPulsaRepository(Pulsa pulsaPayload) {
        if (mutablePulsaLiveData == null) {
            pulsaRepository = PulsaRepository.getInstance();
        }
        mutablePulsaLiveData = pulsaRepository.postPulsa(pulsaPayload);
        return mutablePulsaLiveData;
    }



}