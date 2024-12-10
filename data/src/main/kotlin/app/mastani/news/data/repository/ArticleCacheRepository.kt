package app.mastani.news.data.repository

import app.mastani.news.data.datasource.local.database.articles.ArticleEntity

interface ArticleCacheRepository {
    suspend fun getCachedArticles(): List<ArticleEntity>
    suspend fun addCachedArticles(articles: List<ArticleEntity>)
    suspend fun deleteCachedArticles()
}