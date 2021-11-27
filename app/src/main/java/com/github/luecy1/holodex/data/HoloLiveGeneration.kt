package com.github.luecy1.holodex.data

import android.content.Context
import com.github.luecy1.holodex.R

enum class HoloLiveGeneration(
    val id: Int
) {

    Zero(1),
    First(2),
    Second(3),
    Gamers(4),
    Third(5),
    Fourth(6),
    Fifth(7);

    fun getDisplayName(context: Context): String {
        val stringArray = context.resources.getStringArray(R.array.hololive_generation)
        return stringArray[id - 1]
    }
}