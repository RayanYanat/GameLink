package com.example.gamelink.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gamelink.model.*
import com.example.gamelink.repository.LolApiRepository

class LolApiViewModel(app: Application)  : AndroidViewModel(app) {

    private var lolApiRepository = LolApiRepository(app)

    val response : LiveData<SummonerData>
    val matchHistoryResponse : LiveData<List<String>>
    private val matchDetailData : LiveData<DetailGameData>
    val rankDetailData : LiveData<List<ResponseItem?>?>

    init {
        response = lolApiRepository.response
        matchHistoryResponse = lolApiRepository.matchHistoryResponse
        matchDetailData = lolApiRepository.matchDetailResponse
        rankDetailData = lolApiRepository.rankDetailsResponse
    }

    fun getSummonerIds( summonerName : String, key : String) {
        lolApiRepository.getSummonerID(summonerName, key)
    }

    fun summonerIds( summonerName : String, key : String): LiveData<SummonerData> {
        return lolApiRepository.getSummonerID(summonerName, key)
    }

    fun  MatchHistoryIds (puuid: String,start: Int,count:Int, key: String) : LiveData<List<String>>{
        return lolApiRepository.getMatchHistoryID(puuid, start, count, key)
    }

    fun getMatchHistoryIds (puuid: String,start: Int,count:Int, key: String){
        lolApiRepository.getMatchHistoryID(puuid, start, count, key)
    }

    fun getMatchDetailData (matchId: String, key: String){
        lolApiRepository.getMatchDetailStat(matchId, key)
    }

    fun matchDetailData (matchId: String, key: String) : MutableLiveData<DetailGameData> {
       return lolApiRepository.getMatchDetailStat(matchId, key)
    }

    fun getRankDetailData (summonerId: String, key: String){
        lolApiRepository.getRankDetails(summonerId, key)
    }

}