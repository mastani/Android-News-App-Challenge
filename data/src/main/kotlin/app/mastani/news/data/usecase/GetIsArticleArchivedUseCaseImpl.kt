package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.domain.usecase.GetIsArticleArchivedUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetIsArticleArchivedUseCaseImpl @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetIsArticleArchivedUseCase {

    override suspend fun invoke(title: String): Resource<Boolean> {
        return withContext(dispatcher) {
            val articles = archiveRepository.isArticleArchived(title)
            Resource.Success(articles)
        }
    }
}