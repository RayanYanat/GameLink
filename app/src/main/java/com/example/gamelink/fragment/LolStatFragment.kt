package com.example.gamelink.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gamelink.R
import com.example.gamelink.model.SummonerData
import com.example.gamelink.viewModel.LolApiViewModel
import kotlinx.android.synthetic.main.fragment_lol_stat.*

class LolStatFragment : Fragment() {

    private lateinit var mViewModel: LolApiViewModel
    private val apiKey = "RGAPI-f16eb781-71fb-4093-b05d-b3c502cb6b38"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lol_stat, container, false)
        mViewModel = ViewModelProviders.of(this).get(LolApiViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        summoner_search.setOnClickListener {
            val summonerName = summoner_name.text.toString()
            mViewModel.getSummonerIds(summonerName,apiKey)
            mViewModel.response.observe(viewLifecycleOwner, Observer {
                summoner_ID.text = it.id
                mViewModel.getMatchHistoryIds(it.puuid!!,0,10,apiKey)
                mViewModel.matchHistoryResponse.observe(viewLifecycleOwner, Observer {
                    Log.d("LolStatFragment", "LolStatFragment:" + it.get(0))
                    mViewModel.getMatchDetailData(it.get(0), apiKey)
                    mViewModel.matchDetailData.observe(viewLifecycleOwner, Observer {
                        Log.d("LolStatFragment", "LolStatFragment:" + it.info!!.participants!!.get(0)!!.championId)
                    })
                })
            })

        }
    }
}

