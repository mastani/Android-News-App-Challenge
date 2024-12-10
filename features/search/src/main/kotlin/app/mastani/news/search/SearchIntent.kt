package app.mastani.news.search

sealed class SearchIntent {
    data class SearchNews(val query: String): SearchIntent()
}