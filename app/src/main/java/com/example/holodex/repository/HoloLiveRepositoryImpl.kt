package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.GenerationItem
import com.example.holodex.data.HololiverItem
import com.example.holodex.data.Result
import com.example.holodex.data.Result.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoloLiveRepositoryImpl(
    private val context: Context
) : HoloLiveRepository {

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> =
        withContext(Dispatchers.IO) {

            val generationList = mutableListOf<GenerationItem>()

            val hololiverList = mutableListOf<HololiverItem>()

            context.assets.open("HoloLiveMember.csv").bufferedReader().readLines()
                .filter { it.isNotBlank() }
                .map {
                    val (id, name, imageUrl) = it.split(",")

                hololiverList.add(
                    HololiverItem(
                        id = id.toInt(),
                        name = name,
                        imageUrl = imageUrl
                    )
                )
            }

        context.assets.open("HoloLiveGeneration.csv").bufferedReader().readLines()
            .filter { it.isNotBlank() }
            .map { line ->
                val (id, name) = line.split(",")

                val holoLiverList = mutableListOf(
                    hololiverList.random(),
                    hololiverList.random(),
                    hololiverList.random(),
                    hololiverList.random()
                )

                val generationItem = GenerationItem(
                    id = id.toInt(),
                    name = name,
                    hololiverList = holoLiverList
                )

                generationList.add(generationItem)
            }
            return@withContext Success(generationList)
    }
}