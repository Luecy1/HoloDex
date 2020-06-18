package com.github.luecy1.holodex.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HololiverItem(
    val id: Int,
    val name: String,
    val twitterScreenName: String?,
    val imageUrl: String,
    val generation: List<String>,
    val basicInfo: String? = null,
    val channelId: String? = null,
    val fanArtHashTag: String? = null
) : Parcelable
