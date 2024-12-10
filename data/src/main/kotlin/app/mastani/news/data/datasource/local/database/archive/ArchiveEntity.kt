package app.mastani.news.data.datasource.local.database.archive

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archive")
data class ArchiveEntity(
    @PrimaryKey
    val title: String,
    val source: String,
    val author: String,
    val description: String,
    val url: String,
    val imageUrl: String? = null,
    val publishedAt: String?,
    val content: String
)