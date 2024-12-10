package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.NewsListModelMapper
import app.mastani.news.data.repository.NewsRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.GetHeadlinesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHeadlinesUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val newsListModelMapper: NewsListModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetHeadlinesUseCase {

    override suspend fun invoke(): Resource<List<NewsArticle>> {
        return withContext(dispatcher) {
            when (val resource = newsRepository.getHeadlines()) {
                is Resource.Success -> {
                    Resource.Success(newsListModelMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}