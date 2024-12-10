package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.ArticleEntityModelMapper
import app.mastani.news.data.repository.ArticleCacheRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.GetCachedArticleUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCachedArticleUseCaseImpl @Inject constructor(
    private val articleCacheRepository: ArticleCacheRepository,
    private val articleEntityModelMapper: ArticleEntityModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetCachedArticleUseCase {

    override suspend fun invoke(): Resource<List<NewsArticle>> {
        return withContext(dispatcher) {
            val articles = articleCacheRepository.getCachedArticles()
            Resource.Success(articleEntityModelMapper.mapToDomainModel(articles))
        }
    }
}