package com.utilityapp.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File

object FileUtils {
    fun getOutputDirectory(context: Context): File {
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, "UtilityApp").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else context.filesDir
    }

    fun getPdfOutputFile(context: Context, filename: String): File {
        val outputDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS
            ),
            "UtilityApp"
        ).apply { mkdirs() }
        
        return File(outputDir, "$filename.pdf")
    }

    fun getImageOutputFile(context: Context, filename: String): File {
        val outputDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ),
            "UtilityApp"
        ).apply { mkdirs() }
        
        return File(outputDir, "$filename.jpg")
    }

    fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("temp", null, context.cacheDir)
        inputStream?.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return tempFile
    }
}