package com.example.juraganpulsa.networking;

import com.example.juraganpulsa.model.Pulsa;
import com.example.juraganpulsa.model.PulsaListResponse;
import com.example.juraganpulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PulsaApi {
    @GET("product")
    Call<PulsaListResponse> getPulsaList(@Query("page") String page,
                                          @Query("limit") String limit
    );

    @POST("product")
    Call<PulsaResponse> postPulsa(@Body Pulsa body);

}
