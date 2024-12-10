package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.ArticleArchiveEntityModelMapper
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.GetArchiveArticleUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArchiveArticleUseCaseImpl @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    private val articleArchiveEntityModelMapper: ArticleArchiveEntityModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetArchiveArticleUseCase {

    override suspend fun invoke(): Resource<List<NewsArticle>> {
        return withContext(dispatcher) {
            val articles = archiveRepository.getArchiveArticles()
            Resource.Success(
                articles.map {
                    articleArchiveEntityModelMapper.mapToDomainModel(it)
                }
            )
        }
    }
}