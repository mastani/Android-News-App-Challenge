package app.mastani.news.data.datasource.remote

import app.mastani.news.data.model.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=us")
    suspend fun getHeadlines(
        @Query("language") language: String = "en"
    ): Response<NewsListResponse>

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("language") language: String = "en"
    ): Response<NewsListResponse>
}