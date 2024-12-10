package app.mastani.news.detail

import app.mastani.news.domain.model.NewsArticle

sealed class DetailIntent {
    data class AddArticleToArchive(val newsArticle: NewsArticle) : DetailIntent()
    data class RemoveArticleFromArchive(val newsArticle: NewsArticle) : DetailIntent()
}