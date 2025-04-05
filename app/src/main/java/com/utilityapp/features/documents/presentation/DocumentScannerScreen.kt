package com.utilityapp.features.documents.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FlipCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.utilityapp.R
import com.utilityapp.features.documents.presentation.components.CameraPreview
import com.utilityapp.util.FileUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentScannerScreen(
    onDocumentScanned: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )
    
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                CameraController.IMAGE_ANALYSIS
            )
        }
    }
    
    var outputDirectory by remember { mutableStateOf(FileUtils.getOutputDirectory(context)) }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    
    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
    
    Scaffold(
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        controller.cameraSelector = when (controller.cameraSelector) {
                            CameraSelector.DEFAULT_BACK_CAMERA -> 
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            else -> 
                                CameraSelector.DEFAULT_BACK_CAMERA
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FlipCamera,
                        contentDescription = "Flip Camera"
                    )
                }
                
                FloatingActionButton(
                    onClick = {
                        val photoFile = File(
                            outputDirectory,
                            SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US)
                                .format(System.currentTimeMillis()) + ".jpg"
                        )
                        
                        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                        
                        controller.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                    val savedUri = Uri.fromFile(photoFile)
                                    capturedImageUri = savedUri
                                    onDocumentScanned(savedUri)
                                }
                                
                                override fun onError(exc: ImageCaptureException) {
                                    // Handle error
                                }
                            }
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = "Take Photo"
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (hasCameraPermission) {
                CameraPreview(
                    controller = controller,
                    lifecycleOwner = lifecycleOwner
                )
            } else {
                Text(
                    text = "Camera permission required",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}