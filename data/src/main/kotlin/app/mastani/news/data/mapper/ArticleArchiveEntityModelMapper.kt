package app.mastani.news.data.mapper

import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity
import app.mastani.news.domain.model.NewsArticle
import javax.inject.Inject

class ArticleArchiveEntityModelMapper @Inject constructor() : Mapper<ArchiveEntity, NewsArticle> {

    override fun mapToDomainModel(responseModel: ArchiveEntity): NewsArticle {
        return NewsArticle(
            source = responseModel.source,
            author = responseModel.author,
            title = responseModel.title,
            description = responseModel.description,
            url = responseModel.url,
            imageUrl = responseModel.imageUrl ?: "",
            publishedAt = responseModel.publishedAt ?: "",
            content = responseModel.content
        )
    }
}