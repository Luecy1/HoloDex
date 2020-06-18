package com.github.luecy1.holodex.repository

import android.content.Context
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.api.HololiveAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RemoteHoloLiveRepository constructor(
    private val context: Context,
    private val hololiveService: HololiveAPIService
) : HoloLiveRepository {

    var cache: Result<List<GenerationItem>>? = null

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> {

        cache?.let {
            return it
        }

        return withContext(Dispatchers.IO) {

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
                        HololiverItem(
                            id = it.id,
                            name = it.name,
                            twitterScreenName = it.twitterScreenName,
                            imageUrl = it.imageUrl,
                            generation = it.generation,
                            basicInfo = it.basicInfo,
                            channelId = it.channelId,
                            fanArtHashTag = it.fanArtHashTag
                        )
                    }

                    GenerationItem(
                        id = generationId,
                        name = name,
                        hololiverList = generationMember
                    )
                }

                return@withContext Result.Success(generationList).also {
                    cache = it
                }
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext Result.Error(e)
            }
        }
    }
}
