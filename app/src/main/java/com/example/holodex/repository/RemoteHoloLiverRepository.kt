package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.GenerationItem
import com.example.holodex.data.HoloLiverItem
import com.example.holodex.data.Result
import com.example.holodex.repository.api.HololiveAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RemoteHoloLiverRepository constructor(
    private val context: Context,
    private val hololiveService: HololiveAPIService
) : HoloLiverRepository {

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> =
        withContext(Dispatchers.IO) {

            val generationIds = context.assets.open("HoloLiveGeneration.csv").bufferedReader()
                .readLines()
                .filter { it.isNotBlank() }
                .map { line ->
                    val (id, name) = line.split(",")
                    id.toInt() to name
                }

            try {
                val hololiveMembers = hololiveService.hololiveMembers()

                val generationList = generationIds.map { (generationId, name) ->
                    val generationMember = hololiveMembers.filter { hololive ->
                        hololive.generation.contains(generationId.toString())
                    }.map {
                        HoloLiverItem(
                            id = it.id,
                            name = it.name,
                            imageUrl = it.imageUrl
                        )
                    }

                    GenerationItem(
                        id = generationId,
                        name = name,
                        holoLiverList = generationMember
                    )
                }

                return@withContext Result.Success(generationList)
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext Result.Error(e)
            }

        }
}