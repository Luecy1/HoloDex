package com.github.luecy1.holodex.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.HololiverItem


@Composable
fun TopScreen(
    viewModel: HololiveListViewModel = hiltViewModel(),
    onClick: (HololiverItem) -> Unit,
) {
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {

        when (uiState) {
            is HololiveListViewModel.UiState.Initial -> {
                viewModel.initData(false)
            }
            is HololiveListViewModel.UiState.Failure -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is HololiveListViewModel.UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is HololiveListViewModel.UiState.Success -> {
                LiverGroup((uiState as HololiveListViewModel.UiState.Success).data) {
                    onClick(it)
                }
            }
        }
    }
}