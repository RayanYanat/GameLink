package com.example.gamelink.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SummonerData {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("accountId")
    @Expose
    var accountId: String? = null

    @SerializedName("puuid")
    @Expose
    var puuid: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("profileIconId")
    @Expose
    var profileIconId: Int? = null

    @SerializedName("revisionDate")
    @Expose
    var revisionDate: Double? = null

    @SerializedName("summonerLevel")
    @Expose
    var summonerLevel: Int? = null
}

class MatchesHistoryId {
    @SerializedName("ids")
    @Expose
    var ids : String? = null

}