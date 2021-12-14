package com.github.luecy1.holodex.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.luecy1.holodex.style.HoloDexTheme

@Composable
@Preview(showBackground = true)
fun LiverGroupPreview() {
    HoloDexTheme {
        LiverGroup()
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun LiverGroupPreviewNight() {
    HoloDexTheme {
        LiverGroup()
    }
}

@Composable
private fun LiverGroup() {
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        Generation()
        Generation()
        Generation()
        Generation()
        Generation()
        Generation()
        Generation()
    }
}