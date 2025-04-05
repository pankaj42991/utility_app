package com.utilityapp.features.documents.presentation.components

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.utilityapp.R

@Composable
fun QrScannerView(
    onQrScanned: (String) -> Unit,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val context = LocalContext.current
    var barcodeView by remember { mutableStateOf<DecoratedBarcodeView?>(null) }
    val beepManager = remember { BeepManager(context) }

    AndroidView(
        factory = { ctx ->
            DecoratedBarcodeView(ctx).apply {
                val params = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                layoutParams = params
                barcodeView = this
                barcodeView?.decodeContinuous(callback)
            }
        },
        modifier = modifier,
        update = { view ->
            view.resume()
        }
    )

    DisposableEffect(lifecycleOwner) {
        onDispose {
            barcodeView?.pause()
            barcodeView?.stopDecoding()
        }
    }

    val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text != null) {
                beepManager.playBeepSoundAndVibrate()
                onQrScanned(result.text)
            }
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }
}