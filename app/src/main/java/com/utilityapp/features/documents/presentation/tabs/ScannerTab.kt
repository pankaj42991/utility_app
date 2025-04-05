package com.utilityapp.features.documents.presentation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.utilityapp.R
import com.utilityapp.features.documents.presentation.DocumentScannerScreen
import com.utilityapp.features.documents.presentation.DocumentsViewModel

@Composable
fun ScannerTab(
    modifier: Modifier = Modifier,
    viewModel: DocumentsViewModel = hiltViewModel()
) {
    var showScanner by remember { mutableStateOf(false) }

    if (showScanner) {
        DocumentScannerScreen(
            onDocumentScanned = { uri ->
                viewModel.onDocumentScanned(uri)
                showScanner = false
            },
            modifier = modifier.fillMaxSize()
        )
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.scanner_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = { showScanner = true },
                modifier = Modifier.width(200.dp)
            ) {
                Text(stringResource(R.string.start_scanning))
            }
        }
    }
}