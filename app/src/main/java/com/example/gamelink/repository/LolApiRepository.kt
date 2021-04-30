package com.example.gamelink.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.gamelink.model.MatchesHistoryId
import com.example.gamelink.model.SummonerData
import com.example.gamelink.utils.LolApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LolApiRepository(val application: Application) {


    val response = MutableLiveData<SummonerData>()
    val matchHistoryResponse = MutableLiveData<List<String>>()

    fun getSummonerID(summonerName: String, key: String): MutableLiveData<SummonerData> {
     //  val responseData = MutableLiveData<SummonerData>()
       // val response = MutableLiveData<SummonerData>()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://euw1.api.riotgames.com/lol/summoner/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LolApiService::class.java)



        service.getSummonerData(summonerName, key).enqueue(object : Callback<SummonerData> {
            override fun onFailure(call: Call<SummonerData>, t: Throwable) {
                Toast.makeText(application, "Error wile accessing the API" + t, Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "LolApiRepository:" + t)

            }

            override fun onResponse(call: Call<SummonerData>, resp: Response<SummonerData>) {
                Log.d("LolApiRepository", "LolApiRepository:" + resp.toString())
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
                Toast.makeText(application, "Error wile accessing the API" + t, Toast.LENGTH_SHORT)
                    .show()
                Log.d("LolApiRepository", "MatchHistoryID:" + t)

            }

            override fun onResponse(call: Call<List<String>>, resp: Response<List<String>>) {
                Log.d("LolApiRepository", "MatchHistoryID:" + resp.toString())
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
}