package com.github.luecy1.holodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.ui.ImagePreview
import com.github.luecy1.holodex.ui.TopScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContent {
            HoloDexTheme {
                CompositionLocalProvider(ImagePreview provides false) {
                    TopScreen()
                }
            }
        }
    }
}



