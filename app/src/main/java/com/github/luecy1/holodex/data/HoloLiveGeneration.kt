package com.github.luecy1.holodex.data

import android.content.Context
import android.os.Parcelable
import com.github.luecy1.holodex.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class HoloLiveGeneration(
    val id: Int
) : Parcelable {

    Zero(1),
    First(2),
    Second(3),
    Gamers(4),
    Third(5),
    Fourth(6);

    fun getDisplayName(context: Context): String {
        val stringArray = context.resources.getStringArray(R.array.hololive_generation)
        return stringArray[id - 1]
    }
}