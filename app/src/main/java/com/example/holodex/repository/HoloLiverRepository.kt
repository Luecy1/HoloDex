package com.example.holodex.repository

import com.example.holodex.data.HoloLiverItem

interface HoloLiverRepository {
    suspend fun getHoloLiveList(): List<HoloLiverItem>
}