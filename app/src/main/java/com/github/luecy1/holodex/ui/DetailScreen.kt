package com.github.luecy1.holodex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.github.luecy1.holodex.R
import timber.log.Timber

val roundedCornerSize = 10.dp

@Composable
fun DetailScreen(
    viewModel: HololiveDetailViewModel = hiltViewModel(),
    id: String
) {

    val liver = viewModel.findHoloLiver(id)
    Timber.d(liver.toString())

    val imagePainter = if (ImagePreview.current) {
        painterResource(id = R.drawable.lamy)
    } else {
        rememberImagePainter(liver.imageUrl)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = imagePainter,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topStart = roundedCornerSize,
                                        topEnd = roundedCornerSize
                                    )
                                )
                        )
                        Text(text = liver.name)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {

    }
}

