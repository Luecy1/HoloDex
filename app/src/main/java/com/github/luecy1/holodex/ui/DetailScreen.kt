package com.github.luecy1.holodex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.ui.top.HololiveDetailViewModel
import com.github.luecy1.holodex.ui.top.ImagePreview
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

@Composable
@Preview(showBackground = true)
fun BasicInfo() {
    HoloDexTheme {
        Column(
            Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.basic_info),
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "誕生日:4/22\n身長142cm\nファンネーム：へい民\nイラストレーター：おしおしお",
                style = MaterialTheme.typography.body2
            )
        }
    }
}