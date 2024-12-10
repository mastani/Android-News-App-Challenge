package app.mastani.news.data.mapper

import app.mastani.news.data.datasource.local.database.articles.ArticleEntity
import app.mastani.news.domain.model.NewsArticle
import javax.inject.Inject

class ArticleEntityModelMapper @Inject constructor() : Mapper<List<ArticleEntity>, List<NewsArticle>> {

    override fun mapToDomainModel(responseModel: List<ArticleEntity>): List<NewsArticle> {
        return responseModel.map {
            NewsArticle(
                source = it.source,
                author = it.author,
                title = it.title,
                description = it.description ?: "",
                url = it.url,
                imageUrl = it.imageUrl ?: "",
                publishedAt = it.publishedAt ?: "",
                content = it.content
            )
        }
    }
}