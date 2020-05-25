package com.example.holodex.repository

import android.content.Context
import com.example.holodex.data.HoloLiverItem

class HoloLiverRepositoryImpl(
    val context: Context
) : HoloLiverRepository {

    override fun getHoloLiveList(): List<HoloLiverItem> {

        val list = mutableListOf<HoloLiverItem>()

        for (i in 0 until 25) {
            list.add(
                HoloLiverItem(
                    id = i,
                    name = "天音かなた",
                    imageUrl = "https://pbs.twimg.com/profile_images/1263309015661965313/E34lYRNA_400x400.jpg"
                )
            )
        }

        return list
    }
}