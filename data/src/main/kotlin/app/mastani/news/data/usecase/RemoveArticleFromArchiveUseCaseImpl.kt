package app.mastani.news.data.usecase

import app.mastani.news.common.Resource
import app.mastani.news.data.di.module.IoDispatcher
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.domain.usecase.RemoveArticleFromArchiveUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveArticleFromArchiveUseCaseImpl @Inject constructor(
    private val archiveRepository: ArchiveRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : RemoveArticleFromArchiveUseCase {

    override suspend fun invoke(title: String): Resource<Unit> {
        withContext(dispatcher) {
            archiveRepository.removeArticleToArchive(title)
        }

        return Resource.Success(Unit)
    }
}