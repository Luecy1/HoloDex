package com.github.luecy1.holodex.preview_data

import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HoloLiveGeneration
import com.github.luecy1.holodex.data.HololiverItem

val lamyData = HololiverItem(
    id = 0,
    name = "雪花ラミィ",
    twitterScreenName = "yukihanalamy",
    imageUrl = "https://pbs.twimg.com/profile_images/1469238330067877889/imRH03bc_400x400.jpg",
    generation = listOf("7"),
    basicInfo = "デビュー日：2020年8月12日（水）\n誕生日：11月15日\n身長：158cm\nファンネーム：雪民（ゆきみん）\nイラストレーター：リン☆ユウ",
    channelId = "UCFKOVgVbGmX65RxO3EtH3iw",
    fanArtHashTag = "#LamyArt",
)

val tokinosoraData = HololiverItem(
    id = 0,
    name = "ときのそら",
    twitterScreenName = "",
    imageUrl = "https://pbs.twimg.com/profile_images/1455156713678860288/c0ntVQW9_400x400.jpg",
    generation = listOf(),
    basicInfo = "",
    channelId = "",
    fanArtHashTag = "",
)

val minatoAquaData = HololiverItem(
    id = 0,
    name = "湊あくあ",
    twitterScreenName = "",
    imageUrl = "https://pbs.twimg.com/profile_images/1439902455911759877/2sX3zhd0_400x400.jpg",
    generation = listOf(),
    basicInfo = "",
    channelId = "",
    fanArtHashTag = "",
)

val sampleGeneration = GenerationItem(
    id = 1,
    hololiverList = listOf(lamyData, tokinosoraData, minatoAquaData),
    generation = HoloLiveGeneration.Zero
)

val sampleGenerationListList = listOf(sampleGeneration, sampleGeneration, sampleGeneration)