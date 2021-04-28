package com.example.gamelink.utils;

import com.example.gamelink.model.SummonerData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LolApiService {
    @GET("summoners/by-name/")
    Call<SummonerData> getSummonerData (@Query("summonerName")String summonerName, @Query("key") String key);
}
