package com.example.gamelink.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Annonce(val gameAnnonce: String?,
                   val annonceText: String?,
                   val dateAnnonce: String?,
                   val tittle: String?,
                   val username: String ,
                   val uid: String ,
                   val urlPicture: String? ):Parcelable{
                       constructor():this("","","","","","","")
                   }
