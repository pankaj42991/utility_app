package com.utilityapp.features.documents.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.utilityapp.R
import com.utilityapp.features.documents.presentation.components.DocumentTabRow
import com.utilityapp.features.documents.presentation.tabs.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun DocumentsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val tabs = listOf(
        stringResource(R.string.scanner),
        stringResource(R.string.pdf_tools),
        stringResource(R.string.qr_scanner),
        stringResource(R.string.images)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.documents)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            DocumentTabRow(
                tabs = tabs,
                pagerState = pagerState
            )

            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> ScannerTab()
                    1 -> PdfToolsTab()
                    2 -> QrScannerTab()
                    3 -> ImagesTab()
                }
            }
        }
    }
}