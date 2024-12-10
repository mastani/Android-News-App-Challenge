package app.mastani.news.home

sealed class HomeIntent {
    data object FetchHeadlines: HomeIntent()
    data class FetchNews(val query: String): HomeIntent()
}