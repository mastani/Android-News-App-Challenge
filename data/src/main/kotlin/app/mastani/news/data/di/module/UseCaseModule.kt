package app.mastani.news.data.di.module

import app.mastani.news.data.mapper.ArchiveEntityModelMapper
import app.mastani.news.data.mapper.ArticleArchiveEntityModelMapper
import app.mastani.news.data.mapper.ArticleEntityModelMapper
import app.mastani.news.data.mapper.CacheArticleEntityModelMapper
import app.mastani.news.data.mapper.NewsListModelMapper
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.data.repository.ArticleCacheRepository
import app.mastani.news.data.repository.NewsRepository
import app.mastani.news.data.usecase.AddArticleToArchiveUseCaseImpl
import app.mastani.news.data.usecase.AddCachedArticlesUseCaseImpl
import app.mastani.news.data.usecase.GetArchiveArticleUseCaseImpl
import app.mastani.news.data.usecase.GetCachedArticleUseCaseImpl
import app.mastani.news.data.usecase.GetHeadlinesUseCaseImpl
import app.mastani.news.data.usecase.GetIsArticleArchivedUseCaseImpl
import app.mastani.news.data.usecase.GetNewsUseCaseImpl
import app.mastani.news.data.usecase.RemoveArticleFromArchiveUseCaseImpl
import app.mastani.news.domain.usecase.AddArticleToArchiveUseCase
import app.mastani.news.domain.usecase.AddCachedArticlesUseCase
import app.mastani.news.domain.usecase.GetArchiveArticleUseCase
import app.mastani.news.domain.usecase.GetCachedArticleUseCase
import app.mastani.news.domain.usecase.GetHeadlinesUseCase
import app.mastani.news.domain.usecase.GetIsArticleArchivedUseCase
import app.mastani.news.domain.usecase.GetNewsUseCase
import app.mastani.news.domain.usecase.RemoveArticleFromArchiveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository,
        articleCacheRepository: ArticleCacheRepository,
        newsListModelMapper: NewsListModelMapper,
        cacheArticleEntityModelMapper: CacheArticleEntityModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetNewsUseCase = GetNewsUseCaseImpl(
        newsRepository,
        articleCacheRepository,
        newsListModelMapper,
        cacheArticleEntityModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideGetHeadlinesUseCase(
        newsRepository: NewsRepository,
        newsListModelMapper: NewsListModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetHeadlinesUseCase = GetHeadlinesUseCaseImpl(
        newsRepository,
        newsListModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideGetArchiveArticleUseCase(
        archiveRepository: ArchiveRepository,
        articleArchiveEntityModelMapper: ArticleArchiveEntityModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetArchiveArticleUseCase = GetArchiveArticleUseCaseImpl(
        archiveRepository,
        articleArchiveEntityModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideRemoveArticleFromArchiveUseCase(
        archiveRepository: ArchiveRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): RemoveArticleFromArchiveUseCase = RemoveArticleFromArchiveUseCaseImpl(
        archiveRepository,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideGetIsArticleArchivedUseCase(
        archiveRepository: ArchiveRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetIsArticleArchivedUseCase = GetIsArticleArchivedUseCaseImpl(
        archiveRepository,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideAddArticleToArchiveUseCase(
        archiveRepository: ArchiveRepository,
        archiveEntityModelMapper: ArchiveEntityModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): AddArticleToArchiveUseCase = AddArticleToArchiveUseCaseImpl(
        archiveRepository,
        archiveEntityModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideGetCachedArticleUseCase(
        articleCacheRepository: ArticleCacheRepository,
        articleEntityModelMapper: ArticleEntityModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetCachedArticleUseCase = GetCachedArticleUseCaseImpl(
        articleCacheRepository,
        articleEntityModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideAddCachedArticlesUseCase(
        articleCacheRepository: ArticleCacheRepository,
        cacheArticleEntityModelMapper: CacheArticleEntityModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): AddCachedArticlesUseCase = AddCachedArticlesUseCaseImpl(
        articleCacheRepository,
        cacheArticleEntityModelMapper,
        dispatcher
    )
}