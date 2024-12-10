package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.CacheArticleEntityModelMapper
import app.mastani.news.data.mapper.NewsListModelMapper
import app.mastani.news.data.repository.ArticleCacheRepository
import app.mastani.news.data.repository.NewsRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val articleCacheRepository: ArticleCacheRepository,
    private val newsListModelMapper: NewsListModelMapper,
    private val cacheArticleEntityModelMapper: CacheArticleEntityModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetNewsUseCase {

    override suspend fun invoke(query: String, shouldCache: Boolean): Resource<List<NewsArticle>> {
        return withContext(dispatcher) {
            when (val resource = newsRepository.getNews(query = query)) {
                is Resource.Success -> {
                    val articles = newsListModelMapper.mapToDomainModel(resource.data)

                    if (shouldCache) {
                        articleCacheRepository.deleteCachedArticles()
                        articleCacheRepository.addCachedArticles(cacheArticleEntityModelMapper.mapToDomainModel(articles))
                    }

                    Resource.Success(articles)
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}