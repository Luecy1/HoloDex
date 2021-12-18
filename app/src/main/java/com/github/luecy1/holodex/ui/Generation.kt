package com.github.luecy1.holodex.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.preview_data.sampleGeneration
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.style.textColor

@Composable
@Preview(showBackground = true)
fun GenerationPreview() {
    HoloDexTheme {
        Generation(sampleGeneration)
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun GenerationPreviewDark() {
    HoloDexTheme {
        Generation(sampleGeneration)
    }
}

//@Composable
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//fun GenerationPreviewWithImageDark() {
//    HoloDexTheme {
//        CompositionLocalProvider(ImagePreview provides false) {
//            Generation(sampleGeneration)
//        }
//    }
//}

@Composable
fun Generation(generation: GenerationItem) {

    val generationName = stringArrayResource(id = R.array.hololive_generation)[generation.id]

    Column {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .padding(start = 16.dp, bottom = 4.dp)
        ) {
            Text(
                text = generationName,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.textColor,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Divider(
                color = Color(0x90, 0x6B, 0xED),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
        }
        Livers(generation)
    }
}

@Composable
fun Livers(generation: GenerationItem) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(generation.hololiverList) { liver ->
            LiverItem(liver = liver)
        }
    }
}