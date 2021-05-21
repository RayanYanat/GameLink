package com.example.gamelink.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.gamelink.model.*
import com.example.gamelink.utils.LolApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LolApiRepository(val application: Application) {


    val response = MutableLiveData<SummonerData>()
    val matchHistoryResponse = MutableLiveData<List<String>>()
    val matchDetailResponse = MutableLiveData<DetailGameData>()
    val rankDetailsResponse = MutableLiveData<List<ResponseItem?>?>()

    fun getSummonerID(summonerName: String, key: String): MutableLiveData<SummonerData> {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://euw1.api.riotgames.com/lol/summoner/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LolApiService::class.java)



        service.getSummonerData(summonerName, key).enqueue(object : Callback<SummonerData> {
            override fun onFailure(call: Call<SummonerData>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API$t", Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "LolApiRepository:$t")

            }

            override fun onResponse(call: Call<SummonerData>, resp: Response<SummonerData>) {
                Log.d("LolApiRepository", "LolApiRepository:$resp")
                if (resp.body() != null) {
                    Toast.makeText(application, "Success accessing the API", Toast.LENGTH_SHORT)
                        .show()
                    response.value = resp.body()
                } else {
                    Log.d("LolApiRepository", "LolApiRepository:" + resp.errorBody().toString())
//                    Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
//                        .show()
                }
            }

        })
        return response
    }

    fun getMatchHistoryID(puuid: String,start: Int,count:Int, key: String): MutableLiveData<List<String>> {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://europe.api.riotgames.com/lol/match/v5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LolApiService::class.java)



        service.getMatchesHistoryIds(puuid, start, count, key).enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API$t", Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "MatchHistoryID:$t")

            }

            override fun onResponse(call: Call<List<String>>, resp: Response<List<String>>) {
                Log.d("LolApiRepository", "MatchHistoryID:$resp")
                if (resp.body() != null) {
                    Toast.makeText(application, "Success accessing the API", Toast.LENGTH_SHORT)
                        .show()
                    matchHistoryResponse.value = resp.body()
                } else {
                    Log.d("LolApiRepository", "MatchHistoryID:" + resp.errorBody().toString())
//                    Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
//                        .show()
                }
            }

        })
        return matchHistoryResponse
    }

    fun getMatchDetailStat(matchId: String, key: String): MutableLiveData<DetailGameData> {
        val matchDetailResponseData = MutableLiveData<DetailGameData>()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://europe.api.riotgames.com/lol/match/v5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LolApiService::class.java)



        service.getMatchDetails(matchId, key).enqueue(object : Callback<DetailGameData> {
            override fun onFailure(call: Call<DetailGameData>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API$t", Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "MatchDetailData:$t")

            }

            override fun onResponse(call: Call<DetailGameData>, resp: Response<DetailGameData>) {
                Log.d("LolApiRepository", "MatchDetailData:$resp")
                if (resp.body() != null) {
                    Toast.makeText(application, "Success accessing the API", Toast.LENGTH_SHORT)
                        .show()
                   matchDetailResponse.value = resp.body()
                    matchDetailResponseData.value = resp.body()
                } else {
                    Log.d("LolApiRepository", "MatchDetailData:" + resp.errorBody().toString())

                }
            }

        })
        return matchDetailResponseData
    }

    fun getRankDetails(summonerId: String, key: String): MutableLiveData<List<ResponseItem?>?> {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://euw1.api.riotgames.com/lol/league/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LolApiService::class.java)



        service.getRankDetails(summonerId, key).enqueue(object : Callback<List<ResponseItem?>?> {
            override fun onFailure(call: Call<List<ResponseItem?>?>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API$t", Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "LolApiRepository:$t")

            }

            override fun onResponse(call: Call<List<ResponseItem?>?>, resp: Response<List<ResponseItem?>?>) {
                Log.d("LolApiRepository", "LolApiRepository:$resp")
                if (resp.body() != null) {
                    Toast.makeText(application, "Success accessing the API", Toast.LENGTH_SHORT)
                        .show()
                    rankDetailsResponse.value = resp.body()
                } else {
                    Log.d("LolApiRepository", "LolApiRepository:" + resp.errorBody().toString())

                }
            }

        })
        return rankDetailsResponse
    }
}