package com.utilityapp.features.documents.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilityapp.data.local.dao.DocumentDao
import com.utilityapp.data.local.entities.DocumentEntity
import com.utilityapp.util.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val documentDao: DocumentDao
) : ViewModel() {
    private val _documents = MutableStateFlow<List<DocumentEntity>>(emptyList())
    val documents = _documents.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadDocuments()
    }

    fun onDocumentScanned(uri: Uri) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val file = FileUtils.uriToFile(FileUtils.context, uri)
                val document = DocumentEntity(
                    title = "Scan ${Date().time}",
                    path = file.absolutePath,
                    type = "SCAN",
                    size = file.length()
                )
                documentDao.insert(document)
                loadDocuments()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadDocuments() {
        viewModelScope.launch {
            documentDao.getAllDocuments().collect { docs ->
                _documents.value = docs
            }
        }
    }

    fun deleteDocument(document: DocumentEntity) {
        viewModelScope.launch {
            documentDao.delete(document)
        }
    }
}