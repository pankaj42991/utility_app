package com.utilityapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.utilityapp.features.documents.presentation.DocumentsScreen
import com.utilityapp.features.home.presentation.HomeScreen
import com.utilityapp.features.settings.presentation.SettingsScreen
import com.utilityapp.features.tools.presentation.ToolsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("documents") { DocumentsScreen(navController) }
        composable("tools") { ToolsScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}