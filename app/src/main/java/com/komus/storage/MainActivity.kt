package com.komus.storage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.komus.storage.screen.AuthScreen
import com.komus.storage.screen.TypeScreen
import com.komus.storage.screen.razmeshenie.PlacementScreen
import com.komus.storage.ui.theme.StorageTheme
import com.komus.storage.utils.SPHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var spHelper: SPHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StorageTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController() // Создаём NavController для управления навигацией

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            NavigationHost(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "auth", // Начальный экран
        modifier = modifier
    ) {
        // Экран авторизации
        composable("auth") {
            TypeScreen(navController = navController)
        }

        // Добавьте другие экраны, например "task"
        composable("task") {
            // Здесь будет ваш экран заданий
        }

        composable("placement") {
            PlacementScreen(navController = navController)
        }
    }
}
