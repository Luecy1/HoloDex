package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.GenerationItem
import com.example.holodex.data.HoloLiverItem
import com.example.holodex.data.Result
import com.example.holodex.data.Result.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoloLiverRepositoryImpl(
    private val context: Context
) : HoloLiverRepository {

    override suspend fun getHoloLiveList(): Result<List<GenerationItem>> =
        withContext(Dispatchers.IO) {

            val generationList = mutableListOf<GenerationItem>()

            val hololiverList = mutableListOf<HoloLiverItem>()

            context.assets.open("HoloLiveMember.csv").bufferedReader().readLines()
                .filter { it.isNotBlank() }
                .map {
                    val (id, name, imageUrl) = it.split(",")

                hololiverList.add(
                    HoloLiverItem(
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
                    holoLiverList = holoLiverList
                )

                generationList.add(generationItem)
            }
            return@withContext Success(generationList)
    }
}