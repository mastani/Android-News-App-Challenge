package app.mastani.news.data.repository

import app.mastani.news.common.Resource
import app.mastani.news.common.getFormattedDate
import app.mastani.news.data.datasource.local.database.articles.ArticleDao
import app.mastani.news.data.datasource.remote.ApiService
import app.mastani.news.data.datasource.remote.handleAPICall
import app.mastani.news.data.model.NewsListResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getHeadlines(): Resource<NewsListResponse> {
        return handleAPICall {
            apiService.getHeadlines()
        }
    }

    override suspend fun getNews(query: String): Resource<NewsListResponse> {
        return handleAPICall {
            apiService.getNews(
                query = query,
                from = getFormattedDate(4),
                to = getFormattedDate(0)
            )
        }
    }
}