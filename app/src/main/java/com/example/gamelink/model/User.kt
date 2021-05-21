package com.example.gamelink.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var uid: String, var username: String, var profileImageUrl: String, val listAnnonce: List<Annonce>?): Parcelable {
    constructor() : this("", "", "",null)
  }

