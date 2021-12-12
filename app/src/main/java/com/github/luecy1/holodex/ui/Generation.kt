package com.github.luecy1.holodex.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.luecy1.holodex.style.HoloDexTheme


@Composable
@Preview(showBackground = true)
fun hh() {
    HoloDexTheme {
        Column {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "5th Generation",
                    style = MaterialTheme.typography.h5,
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
            Livers()
        }
    }
}

@Composable
private fun Livers() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            LiverItem()
        }
    }
}