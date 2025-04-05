package com.utilityapp.features.documents.presentation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.utilityapp.R
import com.utilityapp.features.documents.presentation.DocumentsViewModel

@Composable
fun PdfToolsTab(
    modifier: Modifier = Modifier,
    viewModel: DocumentsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.pdf_tools),
            style = MaterialTheme.typography.headlineSmall
        )

        FeatureButton(
            text = stringResource(R.string.merge_pdfs),
            onClick = { /* TODO */ }
        )

        FeatureButton(
            text = stringResource(R.string.split_pdf),
            onClick = { /* TODO */ }
        )

        FeatureButton(
            text = stringResource(R.string.compress_pdf),
            onClick = { /* TODO */ }
        )

        FeatureButton(
            text = stringResource(R.string.convert_to_pdf),
            onClick = { /* TODO */ }
        )
    }
}

@Composable
private fun FeatureButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}