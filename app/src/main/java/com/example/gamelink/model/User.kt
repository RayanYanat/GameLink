package com.example.gamelink.model


  data class User(
    val username: String = "",
    val uid: String = "",
    val urlPicture: String? = "",
    val listAnnonce: List<Annonce>? = null
)

