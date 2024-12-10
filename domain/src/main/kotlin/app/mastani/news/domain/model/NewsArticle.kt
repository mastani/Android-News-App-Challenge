package app.mastani.news.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsArticle(
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String? = null,
    val publishedAt: String,
    val content: String
)