package app.mastani.news.data.repository

import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity

interface ArchiveRepository {
    suspend fun getArchiveArticles(): List<ArchiveEntity>
    suspend fun addArticleToArchive(archiveEntity: ArchiveEntity)
    suspend fun removeArticleToArchive(title: String)
    suspend fun isArticleArchived(title: String): Boolean
}