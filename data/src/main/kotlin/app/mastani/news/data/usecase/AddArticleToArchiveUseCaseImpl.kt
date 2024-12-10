package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.mapper.ArchiveEntityModelMapper
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.AddArticleToArchiveUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddArticleToArchiveUseCaseImpl @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    private val archiveEntityModelMapper: ArchiveEntityModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AddArticleToArchiveUseCase {

    override suspend fun invoke(newsArticle: NewsArticle): Resource<Unit> {
        withContext(dispatcher) {
            val article = archiveEntityModelMapper.mapToDomainModel(newsArticle)
            archiveRepository.addArticleToArchive(article)
        }

        return Resource.Success(Unit)
    }
}