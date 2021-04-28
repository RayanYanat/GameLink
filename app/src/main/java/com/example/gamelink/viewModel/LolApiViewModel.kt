package com.example.gamelink.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.gamelink.model.SummonerData
import com.example.gamelink.repository.LolApiRepository

class LolApiViewModel(app: Application)  : AndroidViewModel(app) {

    var lolApiRepository = LolApiRepository(app)

    fun getSummonerIds( summonerName : String, key : String) {
        lolApiRepository.getSummonerID(summonerName, key)
    }

    fun summonerIds( summonerName : String, key : String): LiveData<SummonerData> {
        return lolApiRepository.getSummonerID(summonerName, key)
    }
}