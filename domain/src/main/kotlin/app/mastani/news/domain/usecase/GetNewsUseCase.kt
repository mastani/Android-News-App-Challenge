package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource
import app.mastani.news.domain.model.NewsArticle

interface GetNewsUseCase {
    suspend operator fun invoke(query: String, shouldCache: Boolean): Resource<List<NewsArticle>>
}