package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource
import app.mastani.news.domain.model.NewsArticle

interface AddArticleToArchiveUseCase {
    suspend operator fun invoke(newsArticle: NewsArticle): Resource<Unit>
}