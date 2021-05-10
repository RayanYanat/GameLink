package com.example.gamelink.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val uid: String, val username: String, val profileImageUrl: String, val listAnnonce: List<Annonce>?): Parcelable {
    constructor() : this("", "", "",null)
  }

