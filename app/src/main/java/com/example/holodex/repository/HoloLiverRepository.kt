package com.example.holodex.repository

import com.example.holodex.data.HoloLiverItem

interface HoloLiverRepository {
    fun getHoloLiveList(): List<HoloLiverItem>
}