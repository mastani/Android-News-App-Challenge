package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource
import app.mastani.news.domain.model.NewsArticle

interface GetHeadlinesUseCase {
    suspend operator fun invoke(): Resource<List<NewsArticle>>
}