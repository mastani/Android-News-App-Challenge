package app.mastani.news.data.datasource.local.database.articles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
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