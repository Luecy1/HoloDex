package com.example.holodex.repository

import com.example.holodex.data.Result
import com.example.holodex.detail.StreamItem

interface StreamRepository {
    suspend fun getStreamInfoList(): Result<List<StreamItem>>
}