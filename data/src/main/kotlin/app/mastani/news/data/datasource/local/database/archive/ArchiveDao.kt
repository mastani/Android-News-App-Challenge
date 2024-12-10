package app.mastani.news.data.datasource.local.database.archive

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArchiveDao {
    @Query("SELECT * FROM archive")
    suspend fun getAll(): List<ArchiveEntity>

    @Query("SELECT EXISTS(SELECT * FROM archive WHERE title = :title)")
    suspend fun isArchive(title : String) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(archiveEntity: ArchiveEntity)

    @Query("DELETE FROM archive WHERE title = :title")
    suspend fun delete(title : String)
}