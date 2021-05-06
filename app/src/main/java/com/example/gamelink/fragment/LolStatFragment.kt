package com.example.gamelink.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.adapter.AnnonceAdapter
import com.example.gamelink.adapter.MatchHistoryAdapter
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.DetailGameData
import com.example.gamelink.model.SummonerData
import com.example.gamelink.viewModel.LolApiViewModel
import kotlinx.android.synthetic.main.fragment_lol_stat.*

class LolStatFragment : Fragment(), MatchHistoryAdapter.ItemClickListener {

    private lateinit var mViewModel: LolApiViewModel
    private val apiKey = "RGAPI-b26bf937-c3b6-4748-8bbe-982208d404d4"
    private lateinit var recyclerView: RecyclerView
    private lateinit var listDetailGameData: ArrayList<DetailGameData>
    private lateinit var adapter: MatchHistoryAdapter
    var summonerPuuid: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lol_stat, container, false)
        mViewModel = ViewModelProviders.of(this).get(LolApiViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_data_stat)
        configureRecyclerView()
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        summoner_search.setOnClickListener {
            val summonerName = summoner_name.text.toString()
            mViewModel.getSummonerIds(summonerName, apiKey)

            mViewModel.response.observe(viewLifecycleOwner, Observer {
                summonerPuuid = it.puuid
                val summonerEncryptedId = it.id

                val profilIconUrl = "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/profileicon/${it.profileIconId}.png"
                Glide.with(requireContext()).load(profilIconUrl).into(profil_icon_img)

                summoner_name_icon.text = it.name
                summoner_lvl_icon.text = "lvl ${it.summonerLevel}"

                mViewModel.getRankDetailData(summonerEncryptedId!!,apiKey)
                mViewModel.rankDetailData.observe(viewLifecycleOwner, Observer {

                    val rankedTierLeague = it?.get(0)!!.tier

                    match_history_tittle.text = "Match History:"

                    summoner_queue_type.text = it.get(0)!!.queueType
                    summoner_ranked_tier.text = rankedTierLeague
                    summoner_ranked_rank.text = it.get(0)!!.rank
                    summoner_ranked_lp.text = "${it.get(0)!!.leaguePoints.toString()} LP"
                    summoner_ranked_loss.text = "${it.get(0)!!.losses.toString()}L"
                    summoner_ranked_win.text = "${it.get(0)!!.wins.toString()}W"

                    updateRankTierIcon(rankedTierLeague)

                })

                mViewModel.getMatchHistoryIds(summonerPuuid!!, 0, 10, apiKey)
                mViewModel.matchHistoryResponse.observe(viewLifecycleOwner, Observer {
                    Log.d("LolStatFragment", "LolStatFragmentDetailGameDataList0:" + it)

                    it.forEach { matchId ->
                        mViewModel.matchDetailData(matchId, apiKey)
                            .observe(viewLifecycleOwner, Observer { detailGameData ->
                                Log.d("LolStatFragment", "LolStatFragmentDetailGameDataListMatchId:" + matchId)
                                listDetailGameData.add(detailGameData)
                                updateUI(listDetailGameData)

                            })
                    }
                })
            })
        }
    }

    private fun configureRecyclerView() {
        this.listDetailGameData = ArrayList()
        adapter = MatchHistoryAdapter(listDetailGameData, this, summonerPuuid)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun updateUI(results: List<DetailGameData>) {

        adapter.setResults(results)
        Log.d("LolStatFragment", "LolStatFragmentList:" + results.size)
        adapter.setParticipantPuuid(summonerPuuid!!)
        Log.d("LolStatFragment", "LolStatFragmentListPuuid:" + summonerPuuid)
        adapter.notifyDataSetChanged()

    }

    private fun updateRankTierIcon (rankTier : String?){
        when (rankTier){
            "GRANDMASTER" -> Glide.with(this).load(R.drawable.emblem_grandmaster).into(division_embleme_img)
            "CHALLENGER" -> Glide.with(this).load(R.drawable.emblem_challenger).into(division_embleme_img)
            "MASTER" -> Glide.with(this).load(R.drawable.emblem_master).into(division_embleme_img)
            "DIAMOND" -> Glide.with(this).load(R.drawable.emblem_diamond).into(division_embleme_img)
            "PLATINUM" -> Glide.with(this).load(R.drawable.emblem_platinum).into(division_embleme_img)
            "GOLD" -> Glide.with(this).load(R.drawable.emblem_gold).into(division_embleme_img)
            "SILVER" -> Glide.with(this).load(R.drawable.emblem_silver).into(division_embleme_img)
            "BRONZE" -> Glide.with(this).load(R.drawable.emblem_bronze).into(division_embleme_img)

        }
    }

    override fun onItemClickListener(detailGame: DetailGameData) {
    }
}

