package com.example.holodex.repository

import com.example.holodex.data.GenerationItem
import com.example.holodex.data.Result

interface HoloLiverRepository {
    suspend fun getHoloLiveList(): Result<List<GenerationItem>>
}