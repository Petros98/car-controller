package com.example.carcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carcontroller.ui.BottomNavigation
import com.example.carcontroller.ui.MenuSections
import com.example.carcontroller.ui.screens.home.HomeScreen
import com.example.carcontroller.ui.theme.CarControllerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarControllerTheme {
                MainContent()
            }
        }
    }
}

@Composable
private fun MainContent() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { paddingValues ->
        NavGraph(modifier = Modifier.padding(paddingValues), navController = navController)
    }
}

@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MenuSections.HOME.route
    ) {
        composable(MenuSections.HOME.route) {
            HomeScreen(modifier)
        }
        composable(MenuSections.VEHICLE.route) {
        }
        composable(MenuSections.LOCATION.route) {
        }
        composable(MenuSections.SETTINGS.route) {
        }
    }
}