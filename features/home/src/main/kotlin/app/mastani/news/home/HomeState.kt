package app.mastani.news.home

import app.mastani.news.domain.model.NewsArticle

sealed class HeadlinesState {
    data object Loading : HeadlinesState()
    data class Success(val articles: List<NewsArticle>) : HeadlinesState()
    data class Error(val error: String?) : HeadlinesState()
}

sealed class NewsState {
    data object Loading : NewsState()
    data class Success(val articles: List<NewsArticle>, val isFromCache: Boolean) : NewsState()
    data class Error(val error: String?) : NewsState()
}