package com.github.luecy1.holodex.repository.api.data

data class HololiveItem(
    val id: Int,
    val name: String,
    val twitterScreenName: String?,
    val imageUrl: String,
    val generation: List<String>,
    val basicInfo: String,
    val channelId: String,
    val fanArtHashTag: String
)
