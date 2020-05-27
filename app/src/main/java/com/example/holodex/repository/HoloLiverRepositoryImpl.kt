package com.example.holodex.repository

import android.content.Context
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
}