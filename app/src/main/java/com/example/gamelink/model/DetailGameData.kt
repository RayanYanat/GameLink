package com.example.gamelink.model

import com.google.gson.annotations.SerializedName

data class DetailGameData(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(

	@field:SerializedName("gameId")
	val gameId: Long? = null,

	@field:SerializedName("gameType")
	val gameType: String? = null,

	@field:SerializedName("queueId")
	val queueId: Int? = null,

	@field:SerializedName("gameDuration")
	val gameDuration: Int? = null,

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null,

	@field:SerializedName("gameStartTimestamp")
	val gameStartTimestamp: Long? = null,

	@field:SerializedName("platformId")
	val platformId: String? = null,

	@field:SerializedName("gameCreation")
	val gameCreation: Long? = null,

	@field:SerializedName("gameName")
	val gameName: String? = null,

	@field:SerializedName("gameVersion")
	val gameVersion: String? = null,

	@field:SerializedName("mapId")
	val mapId: Int? = null,

	@field:SerializedName("gameMode")
	val gameMode: String? = null,

	@field:SerializedName("participants")
	val participants: List<ParticipantsItem?>? = null
)

data class StatPerks(

	@field:SerializedName("offense")
	val offense: Int? = null,

	@field:SerializedName("defense")
	val defense: Int? = null,

	@field:SerializedName("flex")
	val flex: Int? = null
)

data class Perks(

	@field:SerializedName("statPerks")
	val statPerks: StatPerks? = null,

	@field:SerializedName("styles")
	val styles: List<StylesItem?>? = null
)

data class SelectionsItem(

	@field:SerializedName("perk")
	val perk: Int? = null,

	@field:SerializedName("var3")
	val var3: Int? = null,

	@field:SerializedName("var2")
	val var2: Int? = null,

	@field:SerializedName("var1")
	val var1: Int? = null
)

data class ParticipantsItem(

	@field:SerializedName("bountyLevel")
	val bountyLevel: Int? = null,

	@field:SerializedName("totalUnitsHealed")
	val totalUnitsHealed: Int? = null,

	@field:SerializedName("largestMultiKill")
	val largestMultiKill: Int? = null,

	@field:SerializedName("spell2Casts")
	val spell2Casts: Int? = null,

	@field:SerializedName("champExperience")
	val champExperience: Int? = null,

	@field:SerializedName("summonerLevel")
	val summonerLevel: Int? = null,

	@field:SerializedName("damageDealtToObjectives")
	val damageDealtToObjectives: Int? = null,

	@field:SerializedName("magicDamageTaken")
	val magicDamageTaken: Int? = null,

	@field:SerializedName("perks")
	val perks: Perks? = null,

	@field:SerializedName("deaths")
	val deaths: Int? = null,

	@field:SerializedName("objectivesStolen")
	val objectivesStolen: Int? = null,

	@field:SerializedName("detectorWardsPlaced")
	val detectorWardsPlaced: Int? = null,

	@field:SerializedName("magicDamageDealtToChampions")
	val magicDamageDealtToChampions: Int? = null,

	@field:SerializedName("wardsKilled")
	val wardsKilled: Int? = null,

	@field:SerializedName("pentaKills")
	val pentaKills: Int? = null,

	@field:SerializedName("spell3Casts")
	val spell3Casts: Int? = null,

	@field:SerializedName("firstTowerKill")
	val firstTowerKill: Boolean? = null,

	@field:SerializedName("individualPosition")
	val individualPosition: String? = null,

	@field:SerializedName("riotIdName")
	val riotIdName: String? = null,

	@field:SerializedName("wardsPlaced")
	val wardsPlaced: Int? = null,

	@field:SerializedName("totalDamageDealt")
	val totalDamageDealt: Int? = null,

	@field:SerializedName("largestKillingSpree")
	val largestKillingSpree: Int? = null,

	@field:SerializedName("totalDamageDealtToChampions")
	val totalDamageDealtToChampions: Int? = null,

	@field:SerializedName("summoner2Id")
	val summoner2Id: Int? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("totalTimeSpentDead")
	val totalTimeSpentDead: Int? = null,

	@field:SerializedName("inhibitorKills")
	val inhibitorKills: Int? = null,

	@field:SerializedName("puuid")
	val puuid: String? = null,

	@field:SerializedName("totalTimeCCDealt")
	val totalTimeCCDealt: Int? = null,

	@field:SerializedName("participantId")
	val participantId: Int? = null,

	@field:SerializedName("profileIcon")
	val profileIcon: Int? = null,

	@field:SerializedName("teamEarlySurrendered")
	val teamEarlySurrendered: Boolean? = null,

	@field:SerializedName("goldSpent")
	val goldSpent: Int? = null,

	@field:SerializedName("unrealKills")
	val unrealKills: Int? = null,

	@field:SerializedName("consumablesPurchased")
	val consumablesPurchased: Int? = null,

	@field:SerializedName("visionScore")
	val visionScore: Int? = null,

	@field:SerializedName("firstBloodKill")
	val firstBloodKill: Boolean? = null,

	@field:SerializedName("longestTimeSpentLiving")
	val longestTimeSpentLiving: Int? = null,

	@field:SerializedName("killingSprees")
	val killingSprees: Int? = null,

	@field:SerializedName("sightWardsBoughtInGame")
	val sightWardsBoughtInGame: Int? = null,

	@field:SerializedName("turretsLost")
	val turretsLost: Int? = null,

	@field:SerializedName("quadraKills")
	val quadraKills: Int? = null,

	@field:SerializedName("item4")
	val item4: Int? = null,

	@field:SerializedName("item3")
	val item3: Int? = null,

	@field:SerializedName("item6")
	val item6: Int? = null,

	@field:SerializedName("item5")
	val item5: Int? = null,

	@field:SerializedName("item0")
	val item0: Int? = null,

	@field:SerializedName("item2")
	val item2: Int? = null,

	@field:SerializedName("summoner1Id")
	val summoner1Id: Int? = null,

	@field:SerializedName("item1")
	val item1: Int? = null,

	@field:SerializedName("totalDamageShieldedOnTeammates")
	val totalDamageShieldedOnTeammates: Int? = null,

	@field:SerializedName("summoner2Casts")
	val summoner2Casts: Int? = null,

	@field:SerializedName("goldEarned")
	val goldEarned: Int? = null,

	@field:SerializedName("nexusLost")
	val nexusLost: Int? = null,

	@field:SerializedName("physicalDamageTaken")
	val physicalDamageTaken: Int? = null,

	@field:SerializedName("champLevel")
	val champLevel: Int? = null,

	@field:SerializedName("totalDamageTaken")
	val totalDamageTaken: Int? = null,

	@field:SerializedName("neutralMinionsKilled")
	val neutralMinionsKilled: Int? = null,

	@field:SerializedName("championTransform")
	val championTransform: Int? = null,

	@field:SerializedName("tripleKills")
	val tripleKills: Int? = null,

	@field:SerializedName("damageSelfMitigated")
	val damageSelfMitigated: Int? = null,

	@field:SerializedName("inhibitorsLost")
	val inhibitorsLost: Int? = null,

	@field:SerializedName("largestCriticalStrike")
	val largestCriticalStrike: Int? = null,

	@field:SerializedName("totalHealsOnTeammates")
	val totalHealsOnTeammates: Int? = null,

	@field:SerializedName("summoner1Casts")
	val summoner1Casts: Int? = null,

	@field:SerializedName("damageDealtToBuildings")
	val damageDealtToBuildings: Int? = null,

	@field:SerializedName("magicDamageDealt")
	val magicDamageDealt: Int? = null,

	@field:SerializedName("timePlayed")
	val timePlayed: Int? = null,

	@field:SerializedName("championName")
	val championName: String? = null,

	@field:SerializedName("timeCCingOthers")
	val timeCCingOthers: Int? = null,

	@field:SerializedName("teamPosition")
	val teamPosition: String? = null,

	@field:SerializedName("physicalDamageDealtToChampions")
	val physicalDamageDealtToChampions: Int? = null,

	@field:SerializedName("totalMinionsKilled")
	val totalMinionsKilled: Int? = null,

	@field:SerializedName("visionWardsBoughtInGame")
	val visionWardsBoughtInGame: Int? = null,

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("firstTowerAssist")
	val firstTowerAssist: Boolean? = null,

	@field:SerializedName("championId")
	val championId: Int? = null,

	@field:SerializedName("turretKills")
	val turretKills: Int? = null,

	@field:SerializedName("firstBloodAssist")
	val firstBloodAssist: Boolean? = null,

	@field:SerializedName("trueDamageTaken")
	val trueDamageTaken: Int? = null,

	@field:SerializedName("assists")
	val assists: Int? = null,

	@field:SerializedName("itemsPurchased")
	val itemsPurchased: Int? = null,

	@field:SerializedName("objectivesStolenAssists")
	val objectivesStolenAssists: Int? = null,

	@field:SerializedName("summonerId")
	val summonerId: String? = null,

	@field:SerializedName("damageDealtToTurrets")
	val damageDealtToTurrets: Int? = null,

	@field:SerializedName("totalHeal")
	val totalHeal: Int? = null,

	@field:SerializedName("win")
	val win: Boolean? = null,

	@field:SerializedName("lane")
	val lane: String? = null,

	@field:SerializedName("gameEndedInSurrender")
	val gameEndedInSurrender: Boolean? = null,

	@field:SerializedName("physicalDamageDealt")
	val physicalDamageDealt: Int? = null,

	@field:SerializedName("summonerName")
	val summonerName: String? = null,

	@field:SerializedName("trueDamageDealtToChampions")
	val trueDamageDealtToChampions: Int? = null,

	@field:SerializedName("dragonKills")
	val dragonKills: Int? = null,

	@field:SerializedName("baronKills")
	val baronKills: Int? = null,

	@field:SerializedName("doubleKills")
	val doubleKills: Int? = null,

	@field:SerializedName("nexusKills")
	val nexusKills: Int? = null,

	@field:SerializedName("trueDamageDealt")
	val trueDamageDealt: Int? = null,

	@field:SerializedName("spell1Casts")
	val spell1Casts: Int? = null,

	@field:SerializedName("gameEndedInEarlySurrender")
	val gameEndedInEarlySurrender: Boolean? = null,

	@field:SerializedName("teamId")
	val teamId: Int? = null,

	@field:SerializedName("riotIdTagline")
	val riotIdTagline: String? = null,

	@field:SerializedName("spell4Casts")
	val spell4Casts: Int? = null
)

data class Dragon(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class Champion(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class Inhibitor(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class StylesItem(

	@field:SerializedName("selections")
	val selections: List<SelectionsItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("style")
	val style: Int? = null
)

data class Objectives(

	@field:SerializedName("baron")
	val baron: Baron? = null,

	@field:SerializedName("inhibitor")
	val inhibitor: Inhibitor? = null,

	@field:SerializedName("dragon")
	val dragon: Dragon? = null,

	@field:SerializedName("riftHerald")
	val riftHerald: RiftHerald? = null,

	@field:SerializedName("champion")
	val champion: Champion? = null,

	@field:SerializedName("tower")
	val tower: Tower? = null
)

data class Baron(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class Tower(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class RiftHerald(

	@field:SerializedName("kills")
	val kills: Int? = null,

	@field:SerializedName("first")
	val first: Boolean? = null
)

data class BansItem(

	@field:SerializedName("championId")
	val championId: Int? = null,

	@field:SerializedName("pickTurn")
	val pickTurn: Int? = null
)

data class TeamsItem(

	@field:SerializedName("teamId")
	val teamId: Int? = null,

	@field:SerializedName("bans")
	val bans: List<BansItem?>? = null,

	@field:SerializedName("objectives")
	val objectives: Objectives? = null,

	@field:SerializedName("win")
	val win: Boolean? = null
)

data class Metadata(

	@field:SerializedName("dataVersion")
	val dataVersion: String? = null,

	@field:SerializedName("matchId")
	val matchId: String? = null,

	@field:SerializedName("participants")
	val participants: List<String?>? = null
)
