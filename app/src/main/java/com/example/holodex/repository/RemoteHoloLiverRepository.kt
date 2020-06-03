package com.example.holodex.repository

import com.example.holodex.data.GenerationItem
import com.example.holodex.data.Result
import com.example.holodex.repository.api.HololiveAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteHoloLiverRepository constructor(
    val hololiveService: HololiveAPIService
) : HoloLiverRepository {

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> =
        withContext(Dispatchers.IO) {

            val generationItem = mutableListOf<GenerationItem>()

            try {
                val hololiveMembers = hololiveService.hololiveMembers()
            } catch (e: Exception) {

            }


            return@withContext Result.Success(generationItem)
        }
}