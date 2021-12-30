package com.github.luecy1.holodex.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun DetailScreen(
    viewModel: HololiveDetailViewModel = hiltViewModel(),
    id: String
) {

    val liver = viewModel.findHoloLiver(id)
    Timber.d(liver.toString())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = liver.name) },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {

    }
}

