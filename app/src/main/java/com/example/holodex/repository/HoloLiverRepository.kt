package com.example.holodex.repository

import com.example.holodex.data.GenerationItem

interface HoloLiverRepository {
    suspend fun getHoloLiveList(): List<GenerationItem>
}