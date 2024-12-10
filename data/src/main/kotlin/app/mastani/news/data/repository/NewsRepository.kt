package app.mastani.news.data.repository

import app.mastani.news.common.Resource
import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity
import app.mastani.news.data.datasource.local.database.articles.ArticleEntity
import app.mastani.news.data.model.NewsListResponse

interface NewsRepository {
    suspend fun getHeadlines(): Resource<NewsListResponse>
    suspend fun getNews(query: String): Resource<NewsListResponse>
}