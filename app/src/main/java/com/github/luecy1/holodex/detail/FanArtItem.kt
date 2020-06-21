package com.github.luecy1.holodex.detail

data class FanArtItem(
    val tweetId: Long,
    val avatarUrl: String,
    val name: String,
    val screenName: String,
    val body: String,
    val imageUrl: List<String>,
    val detailUrl: String
) {

    fun inRangeIndexIndexImageUrl(index: Int): Boolean {
        return (imageUrl.size > index)
    }
}
