package com.example.gamelink.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Annonce(
    var gameAnnonce: String?,
    var annonceText: String?,
    var dateAnnonce: String?,
    var tittle: String?,
    var username: String,
    var uid: String,
    var urlPicture: String? ):Parcelable{
                       constructor():this("","","","","","","")
                   }
