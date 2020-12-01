package com.example.ournews.networking;

import com.example.ournews.model.Berita;
import com.example.ournews.model.BeritaListResponse;
import com.example.ournews.model.BeritaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaApi {
    @GET("berita")
    Call<BeritaListResponse> getBeritaList(@Query("page") String page,
                                           @Query("limit") String limit
                                           );

    @POST("berita")
    Call<BeritaResponse> postBerita(@Body Berita body);

}
