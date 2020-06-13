package com.example.holodex.repository.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PreferenceServiceImpl @Inject constructor(
    context: Context
) : PreferenceService {

    private val shardPreference: SharedPreferences =
        context.getSharedPreferences("holodex", Context.MODE_PRIVATE)

    override fun getString(key: String): String {
        return shardPreference.getString(key, "") ?: ""
    }

    override fun setString(key: String, value: String) {
        shardPreference.edit {
            putString(key, value)
        }
    }
}