package app.mastani.news.search

import app.mastani.news.domain.model.NewsArticle

sealed class SearchState {
    data object Loading : SearchState()
    data class Success(val articles: List<NewsArticle>) : SearchState()
    data class Error(val error: String?) : SearchState()
}