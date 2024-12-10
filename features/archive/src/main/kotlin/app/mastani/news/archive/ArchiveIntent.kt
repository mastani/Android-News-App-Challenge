package app.mastani.news.archive

sealed class ArchiveIntent {
    data object FetchArchivedArticles: ArchiveIntent()
}