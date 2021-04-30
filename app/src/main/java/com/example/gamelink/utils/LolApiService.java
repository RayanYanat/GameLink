package com.example.gamelink.utils;

import com.example.gamelink.model.DetailGameData;
import com.example.gamelink.model.SummonerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LolApiService {
    @GET("summoners/by-name/{summonerName}?")
    Call<SummonerData> getSummonerData (@Path("summonerName") String summonerName, @Query("api_key") String key);

    @GET("matches/by-puuid/{puuid}/ids")
    Call<List<String>> getMatchesHistoryIds (@Path("puuid") String puuid, @Query("start") Integer start, @Query("count") Integer count, @Query("api_key") String key);

    @GET("matches/{matchId}")
    Call<DetailGameData> getMatchDetails (@Path("matchId")String matchId, @Query("api_key") String key);
}
