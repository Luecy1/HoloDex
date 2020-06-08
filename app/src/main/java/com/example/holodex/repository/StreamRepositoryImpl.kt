package com.example.holodex.repository

import com.example.holodex.data.Result
import com.example.holodex.detail.StreamItem

class StreamRepositoryImpl : StreamRepository {
    override suspend fun getStreamInfoList(): Result<List<StreamItem>> {
        TODO()
    }
}