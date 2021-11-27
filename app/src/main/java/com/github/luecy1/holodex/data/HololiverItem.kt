package com.github.luecy1.holodex.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HololiverItem(
    val id: Int,
    val name: String,
    val twitterScreenName: String?,
    val imageUrl: String,
    val generation: List<String>,
    val basicInfo: String,
    val channelId: String,
    val fanArtHashTag: String
) : Parcelable
