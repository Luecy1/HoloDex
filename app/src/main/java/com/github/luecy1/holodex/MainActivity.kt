package com.github.luecy1.holodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.ui.HololiveListViewModel
import com.github.luecy1.holodex.ui.ImagePreview
import com.github.luecy1.holodex.ui.TopScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HololiveListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoloDexTheme {
                CompositionLocalProvider(ImagePreview provides false) {
                    Navigation(viewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(hololiveListViewModel: HololiveListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "top") {
        composable("top") {
            TopScreen(hololiveListViewModel)
        }
    }
}