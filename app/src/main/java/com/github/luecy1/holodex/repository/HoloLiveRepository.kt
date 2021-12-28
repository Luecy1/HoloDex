package com.github.luecy1.holodex.repository

import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result

interface HoloLiveRepository {
    suspend fun getHoloLiveList(forceLoad: Boolean): Result<List<GenerationItem>>

    fun findHoloLiver(id: String): HololiverItem
}
