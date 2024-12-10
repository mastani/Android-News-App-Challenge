package app.mastani.news.data.mapper

import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity
import app.mastani.news.domain.model.NewsArticle
import javax.inject.Inject

class ArchiveEntityModelMapper @Inject constructor() : Mapper<NewsArticle, ArchiveEntity> {

    override fun mapToDomainModel(responseModel: NewsArticle): ArchiveEntity {
        return ArchiveEntity(
            source = responseModel.source,
            author = responseModel.author,
            title = responseModel.title,
            description = responseModel.description,
            url = responseModel.url,
            imageUrl = responseModel.imageUrl ?: "",
            publishedAt = responseModel.publishedAt,
            content = responseModel.content
        )
    }
}