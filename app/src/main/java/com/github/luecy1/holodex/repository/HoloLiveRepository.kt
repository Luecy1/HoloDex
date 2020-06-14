package com.github.luecy1.holodex.repository

import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.Result

interface HoloLiveRepository {
    suspend fun getHoloLiveList(): Result<List<GenerationItem>>
}
