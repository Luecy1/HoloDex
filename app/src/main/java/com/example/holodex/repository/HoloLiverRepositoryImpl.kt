package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.HoloLiverItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoloLiverRepositoryImpl(
    val context: Context
) : HoloLiverRepository {

    override suspend fun getHoloLiveList(): List<HoloLiverItem> = withContext(Dispatchers.IO) {

        val list = mutableListOf<HoloLiverItem>()

//        for (i in 0 until 25) {
//            list.add(
//                HoloLiverItem(
//                    id = i,
//                    name = "天音かなた",
//                    imageUrl = "https://pbs.twimg.com/profile_images/1263309015661965313/E34lYRNA_400x400.jpg"
//                )
//            )
//        }

        context.assets.open("HoloLiveMember.csv").bufferedReader().readLines().map {
            val (name, imageUrl) = it.split(",")

            list.add(
                HoloLiverItem(
                    id = 1,
                    name = name,
                    imageUrl = imageUrl
                )
            )

            it.lineSequence()
        }

        return@withContext list
    }
}