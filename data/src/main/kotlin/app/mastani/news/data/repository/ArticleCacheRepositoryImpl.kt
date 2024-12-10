package app.mastani.news.data.repository

import app.mastani.news.data.datasource.local.database.articles.ArticleDao
import app.mastani.news.data.datasource.local.database.articles.ArticleEntity
import javax.inject.Inject

class ArticleCacheRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : ArticleCacheRepository {

    override suspend fun getCachedArticles() = articleDao.getAll()

    override suspend fun addCachedArticles(articles: List<ArticleEntity>) = articleDao.insertAll(articles)

    override suspend fun deleteCachedArticles() = articleDao.deleteAll()
}