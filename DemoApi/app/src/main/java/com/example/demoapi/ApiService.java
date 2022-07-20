package com.example.demoapi;

import android.media.session.MediaSession;

import androidx.browser.trusted.Token;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();

    ApiService apiService = new Retrofit.Builder().baseUrl("https://localhost:5001/").
            addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("api/Login")
    Call<Token> Login(@Query("userName") String userName,
                  @Query("password") String password);

}
