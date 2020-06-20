package com.github.luecy1.holodex.repository

import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HoloLiveGeneration
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.api.HololiveAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RemoteHoloLiveRepository constructor(
    private val hololiveService: HololiveAPIService
) : HoloLiveRepository {

    var cache: Result<List<GenerationItem>>? = null

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> {

        cache?.let {
            return it
        }

        return withContext(Dispatchers.IO) {

            try {

                val hololiveMembers = hololiveService.hololiveMembers()

                val generationList = HoloLiveGeneration.values().map { generation ->

                    // 世代ごとにグルーピングするロジック
                    val generationMember = hololiveMembers.filter { hololive ->
                        hololive.generation.contains(generation.id.toString())
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
                        id = generation.id,
                        generation = generation,
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
