package com.utilityapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val path: String,
    val type: String, // PDF, IMAGE, SCAN
    val size: Long,
    val createdAt: Date = Date(),
    val isStarred: Boolean = false,
    val tags: List<String> = emptyList(),
    val ocrText: String? = null
)