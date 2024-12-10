package app.mastani.news.data.mapper

import app.mastani.news.data.datasource.local.database.articles.ArticleEntity
import app.mastani.news.domain.model.NewsArticle
import javax.inject.Inject

class CacheArticleEntityModelMapper @Inject constructor() : Mapper<List<NewsArticle>, List<ArticleEntity>> {

    override fun mapToDomainModel(responseModel: List<NewsArticle>): List<ArticleEntity> {
        return responseModel.map {
            ArticleEntity(
                source = it.source,
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                imageUrl = it.imageUrl ?: "",
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }
}