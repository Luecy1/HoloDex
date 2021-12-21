package com.github.luecy1.holodex.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.style.HoloDexTheme


@Composable
fun TopScreen(
    hololiveListViewModel: HololiveListViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        LiverGroup()
    }
}

@Composable
@Preview
fun TopScreenPreview() {
    HoloDexTheme {
        TopScreen()
    }
}