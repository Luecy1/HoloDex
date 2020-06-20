package com.github.luecy1.holodex.data

data class GenerationItem(
    val id: Int,
    val generation: HoloLiveGeneration,
    val hololiverList: List<HololiverItem>
)
