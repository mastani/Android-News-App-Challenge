package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource
import app.mastani.news.domain.model.NewsArticle

interface AddCachedArticlesUseCase {
    suspend operator fun invoke(articles: List<NewsArticle>): Resource<Unit>
}