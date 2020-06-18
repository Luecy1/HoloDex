package com.github.luecy1.holodex.repository.api.data

data class HololiveItem(
    val id: Int,
    val name: String,
    val twitterScreenName: String?,
    val imageUrl: String,
    val generation: List<String>,
    val basicInfo: String? = null,
    val channelId: String? = null,
    val fanArtHashTag: String? = null
)
