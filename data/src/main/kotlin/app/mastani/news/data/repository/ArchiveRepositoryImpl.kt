package app.mastani.news.data.repository

import app.mastani.news.data.datasource.local.database.archive.ArchiveDao
import app.mastani.news.data.datasource.local.database.archive.ArchiveEntity
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveDao: ArchiveDao
) : ArchiveRepository {

    override suspend fun getArchiveArticles(): List<ArchiveEntity> = archiveDao.getAll()

    override suspend fun addArticleToArchive(archiveEntity: ArchiveEntity) = archiveDao.insert(archiveEntity)

    override suspend fun removeArticleToArchive(title: String) = archiveDao.delete(title)

    override suspend fun isArticleArchived(title: String) = archiveDao.isArchive(title)
}