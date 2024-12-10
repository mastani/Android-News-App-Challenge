package app.mastani.news.data.di.module

import android.app.Application
import androidx.room.Room
import app.mastani.news.data.datasource.local.database.AppDatabase
import app.mastani.news.data.datasource.local.database.archive.ArchiveDao
import app.mastani.news.data.datasource.local.database.articles.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideArticleDao(database: AppDatabase): ArticleDao = database.articleDao()

    @Provides
    fun provideArchiveDao(database: AppDatabase): ArchiveDao = database.archiveDao()
}