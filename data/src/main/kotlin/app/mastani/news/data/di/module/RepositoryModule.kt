package app.mastani.news.data.di.module

import android.content.Context
import app.mastani.news.data.datasource.local.database.archive.ArchiveDao
import app.mastani.news.data.datasource.local.database.articles.ArticleDao
import app.mastani.news.data.datasource.remote.ApiService
import app.mastani.news.data.repository.AppConfigRepositoryImpl
import app.mastani.news.data.repository.AppConfigRepository
import app.mastani.news.data.repository.ArchiveRepository
import app.mastani.news.data.repository.ArchiveRepositoryImpl
import app.mastani.news.data.repository.ArticleCacheRepository
import app.mastani.news.data.repository.ArticleCacheRepositoryImpl
import app.mastani.news.data.repository.NewsRepository
import app.mastani.news.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAppConfigRepository(@ApplicationContext context: Context): AppConfigRepository = AppConfigRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideNewsRepository(
        apiService: ApiService,
        articleDao: ArticleDao
    ): NewsRepository {
        return NewsRepositoryImpl(
            apiService = apiService,
            articleDao = articleDao
        )
    }

    @Singleton
    @Provides
    fun provideArchiveRepository(
        archiveDao: ArchiveDao
    ): ArchiveRepository {
        return ArchiveRepositoryImpl(
            archiveDao = archiveDao
        )
    }

    @Singleton
    @Provides
    fun provideArticleCacheRepository(
        articleDao: ArticleDao
    ): ArticleCacheRepository {
        return ArticleCacheRepositoryImpl(
            articleDao = articleDao
        )
    }
}