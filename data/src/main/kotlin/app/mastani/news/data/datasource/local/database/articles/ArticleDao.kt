package app.mastani.news.data.datasource.local.database.articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    suspend fun getAll(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articleEntries: List<ArticleEntity>)

    @Query("DELETE FROM article")
    suspend fun deleteAll()
}