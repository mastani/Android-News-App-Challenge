package app.mastani.news.domain.usecase

import app.mastani.news.common.Resource

interface GetIsArticleArchivedUseCase {
    suspend operator fun invoke(title: String): Resource<Boolean>
}