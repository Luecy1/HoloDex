package com.github.luecy1.holodex.repository

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.detail.StreamItem

interface StreamRepository {
    suspend fun getStreamInfoList(): Result<List<StreamItem>>
}
