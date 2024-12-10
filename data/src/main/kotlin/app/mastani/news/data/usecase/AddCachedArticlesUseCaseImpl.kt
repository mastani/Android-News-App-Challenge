package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.CacheArticleEntityModelMapper
import app.mastani.news.data.repository.ArticleCacheRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.AddCachedArticlesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCachedArticlesUseCaseImpl @Inject constructor(
    private val articleCacheRepository: ArticleCacheRepository,
    private val cacheArticleEntityModelMapper: CacheArticleEntityModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AddCachedArticlesUseCase {

    override suspend fun invoke(articles: List<NewsArticle>): Resource<Unit> {
        withContext(dispatcher) {
            val articleEntities = cacheArticleEntityModelMapper.mapToDomainModel(articles)
            articleCacheRepository.addCachedArticles(articleEntities)
        }

        return Resource.Success(Unit)
    }
}