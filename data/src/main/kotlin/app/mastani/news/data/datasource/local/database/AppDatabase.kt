package app.mastani.news.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.mastani.news.data.datasource.local.database.archive.ArchiveDao
import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity
import app.mastani.news.data.datasource.local.database.articles.ArticleDao
import app.mastani.news.data.datasource.local.database.articles.ArticleEntity
import app.mastani.news.data.datasource.local.database.converter.DateConverter

@Database(entities = [ArticleEntity::class, ArchiveEntity::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun archiveDao(): ArchiveDao

    companion object {
        const val DB_NAME = "news_app.db"
    }
}