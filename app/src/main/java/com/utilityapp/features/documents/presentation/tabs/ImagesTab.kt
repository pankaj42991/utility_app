package com.utilityapp.features.documents.presentation.tabs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.utilityapp.R
import com.utilityapp.features.documents.presentation.DocumentsViewModel
import com.utilityapp.util.FileUtils

@Composable
fun ImagesTab(
    modifier: Modifier = Modifier,
    viewModel: DocumentsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var extractedText by remember { mutableStateOf<String?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            selectedImageUri = uri
            extractedText = null
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.image_tools),
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = { imagePicker.launch("image/*") }
        ) {
            Text(stringResource(R.string.select_image))
        }

        selectedImageUri?.let { uri ->
            // TODO: Add image preview component
            Text(
                text = stringResource(R.string.selected_image),
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = {
                    // TODO: Implement OCR text extraction
                    val file = FileUtils.uriToFile(context, uri)
                    viewModel.onDocumentScanned(uri)
                }
            ) {
                Text(stringResource(R.string.extract_text))
            }

            extractedText?.let { text ->
                Text(
                    text = text,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}