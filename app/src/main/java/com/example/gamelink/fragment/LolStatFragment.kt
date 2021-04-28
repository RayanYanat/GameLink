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
    private val apiKey = "api_key=RGAPI-bb27988b-cbb1-4767-b18b-ecac8e90c308"


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
            mViewModel.summonerIds(summonerName,apiKey).observe(viewLifecycleOwner,Observer<SummonerData>{
                summoner_ID.text = it.id
                Log.d("LolStatFragment", "LolStatFragment:" + it.id)
                Toast.makeText(context, "zzzzzzzzz ${it.id}", Toast.LENGTH_SHORT).show()
            })

        }
    }
}

