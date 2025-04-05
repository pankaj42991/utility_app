package com.utilityapp.features.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utilityapp.R
import com.utilityapp.features.home.presentation.components.FeatureCard
import com.utilityapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(features) { feature ->
                FeatureCard(
                    feature = feature,
                    onClick = { navController.navigate(feature.route) }
                )
            }
        }
    }
}

val features = listOf(
    FeatureItem(
        titleRes = R.string.documents,
        iconRes = R.drawable.ic_documents,
        route = Screen.Documents.route
    ),
    FeatureItem(
        titleRes = R.string.tools,
        iconRes = R.drawable.ic_tools,
        route = Screen.Tools.route
    ),
    FeatureItem(
        titleRes = R.string.settings,
        iconRes = R.drawable.ic_settings,
        route = Screen.Settings.route
    )
)