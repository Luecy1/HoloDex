package com.example.holodex.repository

import com.example.holodex.data.GenerationItem
import com.example.holodex.data.Result

interface HoloLiveRepository {
    suspend fun getHoloLiveList(): Result<List<GenerationItem>>
}