package com.amaryllis.servantarchive

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Servant(
    val name: String,
    val kelas: String,
    val tipe: String,
    val illustfull: Int,
    val illust: Int,
    val charinfo: String,
    val profile: String,
    val link: String
) : Parcelable
