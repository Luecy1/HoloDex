package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.GenerationItem
import com.example.holodex.data.HoloLiverItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoloLiverRepositoryImpl(
    private val context: Context
) : HoloLiverRepository {

    override suspend fun getHoloLiveList(): List<HoloLiverItem> = withContext(Dispatchers.IO) {

        val list = mutableListOf<HoloLiverItem>()

        context.assets.open("HoloLiveMember.csv").bufferedReader().readLines()
            .filter { it.isNotBlank() }
            .map {
                val (id, name, imageUrl) = it.split(",")

                list.add(
                    HoloLiverItem(
                        id = id.toInt(),
                        name = name,
                        imageUrl = imageUrl
                    )
                )

                it.lineSequence()
            }

        return@withContext list
    }

    override suspend fun getHoloLiveList2(): List<GenerationItem> = withContext(Dispatchers.IO) {

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
            .mapIndexed { index, line ->
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

//        for (index in 1..5) {
//
//            val filteredList = hololiverList.filter {
//                (it.id % 5) == (index - 1)
//            }
//
//            val generationItem = GenerationItem(
//                id = index,
//                name = "index:$index",
//                holoLiverList = filteredList
//            )
//
//            generationList.add(generationItem)
//        }

        return@withContext generationList
    }
}