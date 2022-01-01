package com.github.luecy1.holodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.ui.DetailScreen
import com.github.luecy1.holodex.ui.top.ImagePreview
import com.github.luecy1.holodex.ui.top.TopScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoloDexTheme {
                CompositionLocalProvider(ImagePreview provides false) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "top") {
        composable("top") { _ ->
            TopScreen(viewModel = hiltViewModel()) {
                navController.navigate("detail/${it.id}")
            }
        }
        composable(
            "detail/{liver}"
        ) { backStackEntry ->
            val liver = backStackEntry.arguments?.getString("liver")!!
            Text(text = liver)
            DetailScreen(hiltViewModel(), liver)
        }
    }
}