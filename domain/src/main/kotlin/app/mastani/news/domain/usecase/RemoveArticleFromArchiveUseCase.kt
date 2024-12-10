package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource

interface RemoveArticleFromArchiveUseCase {
    suspend operator fun invoke(title: String): Resource<Unit>
}