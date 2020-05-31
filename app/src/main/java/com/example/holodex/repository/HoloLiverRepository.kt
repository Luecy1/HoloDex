package com.example.holodex.repository

import com.example.holodex.data.GenerationItem
import com.example.holodex.data.HoloLiverItem

interface HoloLiverRepository {
    suspend fun getHoloLiveList(): List<HoloLiverItem>

    suspend fun getHoloLiveList2(): List<GenerationItem>
}