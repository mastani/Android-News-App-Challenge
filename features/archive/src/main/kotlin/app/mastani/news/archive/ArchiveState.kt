package app.mastani.news.archive

import app.mastani.news.domain.model.NewsArticle

sealed class ArchiveState {
    data object Loading : ArchiveState()
    data class Success(val articles: List<NewsArticle>) : ArchiveState()
    data class Error(val error: String?) : ArchiveState()
}