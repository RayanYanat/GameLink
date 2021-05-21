package com.example.gamelink.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.model.DetailGameData
import com.example.gamelink.model.ParticipantsItem
import kotlinx.android.synthetic.main.lol_match_history_item.view.*

class MatchHistoryAdapter(
    private val listMatchHistoryData: List<DetailGameData>
) :
    RecyclerView.Adapter<MatchHistoryAdapter.MatchHistoryViewHolder>() {

    private lateinit var mData: List<DetailGameData>
    private lateinit var currentParticipantsItem: ParticipantsItem
    private lateinit var participantsPuuid: String


    fun setResults(data: List<DetailGameData>) {
        mData = data
    }

    fun setParticipantPuuid(puuid: String) {
        participantsPuuid = puuid
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchHistoryViewHolder {
        return MatchHistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.lol_match_history_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(
        holder: MatchHistoryViewHolder,
        position: Int
    ) {
        val matchHistoryItem = mData[position]

        matchHistoryItem.info?.participants?.forEach {

            if (participantsPuuid == it!!.puuid) {
                currentParticipantsItem = it

            }
        }

        holder.championLevel.text = currentParticipantsItem.champLevel.toString()
        holder.typeOfTheGame.text = matchHistoryItem.info!!.gameMode

        val urlChampImg =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/champion/${currentParticipantsItem.championName}.png"
        Glide.with(holder.itemView).load(urlChampImg).into(holder.championImage)


        holder.playerFarm.text = "${currentParticipantsItem.totalMinionsKilled} cs"
        holder.playerGold.text = "${currentParticipantsItem.goldEarned} g"
        holder.playerKda.text =
            "${currentParticipantsItem.kills}/${currentParticipantsItem.deaths}/${currentParticipantsItem.assists}"

        Log.d(
            "MatchHistoryAdapter",
            "MatchHistoryAdapterChampSummoner1:" + currentParticipantsItem.item0
        )
        if (currentParticipantsItem.win == true) {
            holder.stateEndofGame.text = "VICTORY"
            holder.matchHistoryLayout.setBackgroundColor(Color.parseColor("#8BC34A"))
        } else {
            holder.stateEndofGame.text = "DEFEAT"
            holder.matchHistoryLayout.setBackgroundColor(Color.parseColor("#A54343"))
        }

        val summ1Id = currentParticipantsItem.summoner1Id
        when (summ1Id) {
            21 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerBarrier.png")
                .into(
                    holder.summoner2
                )
            1 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerBoost.png")
                .into(
                    holder.summoner2
                )
            14 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerDot.png")
                .into(
                    holder.summoner2
                )
            3 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerExhaust.png")
                .into(
                    holder.summoner2
                )
            4 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerFlash.png")
                .into(
                    holder.summoner2
                )
            6 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerHaste.png")
                .into(
                    holder.summoner2
                )
            7 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerHeal.png")
                .into(
                    holder.summoner2
                )
            13 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerMana.png")
                .into(
                    holder.summoner2
                )
            11 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerSmite.png")
                .into(
                    holder.summoner2
                )
            12 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerTeleport.png")
                .into(
                    holder.summoner2
                )
            32 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerSnowball.png")
                .into(
                    holder.summoner2
                )
        }

        val summ2Id = currentParticipantsItem.summoner2Id
        when (summ2Id) {
            21 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerBarrier.png")
                .into(
                    holder.summoner1
                )
            1 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerBoost.png")
                .into(
                    holder.summoner1
                )
            14 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerDot.png")
                .into(
                    holder.summoner1
                )
            3 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerExhaust.png")
                .into(
                    holder.summoner1
                )
            4 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerFlash.png")
                .into(
                    holder.summoner1
                )
            6 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerHaste.png")
                .into(
                    holder.summoner1
                )
            7 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerHeal.png")
                .into(
                    holder.summoner1
                )
            13 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerMana.png")
                .into(
                    holder.summoner1
                )
            11 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerSmite.png")
                .into(
                    holder.summoner1
                )
            12 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerTeleport.png")
                .into(
                    holder.summoner1
                )
            32 -> Glide.with(holder.itemView)
                .load("https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerSnowball.png")
                .into(
                    holder.summoner2
                )
        }

        val urlNoItem =
            "https://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/SummonerDarkStarChampSelect1.png"

        val urlItem1 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item0}.png"
        if (currentParticipantsItem.item0 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item1)
        } else {
            Glide.with(holder.itemView).load(urlItem1).into(holder.item1)
        }

        val urlItem2 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item1}.png"
        if (currentParticipantsItem.item1 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item2)
        } else {
            Glide.with(holder.itemView).load(urlItem2).into(holder.item2)
        }


        val urlItem3 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item2}.png"
        if (currentParticipantsItem.item2 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item3)
        } else {
            Glide.with(holder.itemView).load(urlItem3).into(holder.item3)
        }


        val urlItem4 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item3}.png"
        if (currentParticipantsItem.item3 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item4)
        } else {
            Glide.with(holder.itemView).load(urlItem4).into(holder.item4)
        }


        val urlItem5 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item4}.png"
        if (currentParticipantsItem.item4 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item5)
        } else {
            Glide.with(holder.itemView).load(urlItem5).into(holder.item5)
        }


        val urlItem6 =
            "https://ddragon.leagueoflegends.com/cdn/11.9.1/img/item/${currentParticipantsItem.item5}.png"
        if (currentParticipantsItem.item5 == 0) {
            Glide.with(holder.itemView).load(urlNoItem).into(holder.item6)
        } else {
            Glide.with(holder.itemView).load(urlItem6).into(holder.item6)
        }

    }

    override fun getItemCount(): Int {
        Log.d("AnnonceAdapter", "AnnonceAdapter:" + listMatchHistoryData.size)
        return if (listMatchHistoryData.isNotEmpty()) listMatchHistoryData.size else 0
    }

    class MatchHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val matchHistoryLayout: ConstraintLayout = view.match_history_item_layout
        val championLevel: TextView = view.lvl_champ_history
        val championImage: ImageView = view.image_champ_match_history
        val stateEndofGame: TextView = view.state_end_of_the_game
        val typeOfTheGame: TextView = view.type_of_the_game
        val summoner1: ImageView = view.summoner_1
        val summoner2: ImageView = view.summoner_2
        val item1: ImageView = view.item_1
        val item2: ImageView = view.item_2
        val item3: ImageView = view.item_3
        val item4: ImageView = view.item_4
        val item5: ImageView = view.item_5
        val item6: ImageView = view.item_6
        val playerKda: TextView = view.player_kda
        val playerFarm: TextView = view.player_farm
        val playerGold: TextView = view.player_gold
    }

    interface ItemClickListener {
        fun onItemClickListener(detailGame: DetailGameData)
    }
}