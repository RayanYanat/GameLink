package com.example.gamelink.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamelink.model.MatchesHistoryId
import com.example.gamelink.model.SummonerData
import com.example.gamelink.repository.LolApiRepository
import retrofit2.adapter.rxjava2.Result.response

class LolApiViewModel(app: Application)  : AndroidViewModel(app) {

    var lolApiRepository = LolApiRepository(app)

    val response : LiveData<SummonerData>
    val matchHistoryResponse : LiveData<List<String>>

    init {
        response = lolApiRepository.response
        matchHistoryResponse = lolApiRepository.matchHistoryResponse
    }

    fun getSummonerIds( summonerName : String, key : String) {
        lolApiRepository.getSummonerID(summonerName, key)
    }

    fun summonerIds( summonerName : String, key : String): LiveData<SummonerData> {
        return lolApiRepository.getSummonerID(summonerName, key)
    }

    fun getMatchHistoryIds (puuid: String,start: Int,count:Int, key: String){
        lolApiRepository.getMatchHistoryID(puuid, start, count, key)
    }

    fun  MatchHistoryIds (puuid: String,start: Int,count:Int, key: String) : LiveData<List<String>>{
        return lolApiRepository.getMatchHistoryID(puuid, start, count, key)
    }
}