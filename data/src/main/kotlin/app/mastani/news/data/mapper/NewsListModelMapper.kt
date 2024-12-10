package app.mastani.news.data.mapper

import app.mastani.news.data.model.NewsListResponse
import app.mastani.news.domain.model.NewsArticle
import javax.inject.Inject

class NewsListModelMapper @Inject constructor() : Mapper<NewsListResponse, List<NewsArticle>> {

    override fun mapToDomainModel(responseModel: NewsListResponse): List<NewsArticle> {
        return responseModel.articles?.map {
            NewsArticle(
                source = it.source?.name ?: "",
                author = it.author ?: "",
                title = it.title ?: "",
                description = it.description ?: "",
                url = it.url ?: "",
                imageUrl = it.urlToImage ?: "",
                publishedAt = it.publishedAt ?: "",
                content = it.content ?: ""
            )
        } ?: listOf()
    }
}