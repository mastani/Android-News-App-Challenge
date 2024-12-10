package app.mastani.news.data.repository

import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {
    suspend fun setIsDarkTheme(isDarkTheme: Boolean)
    fun getIsDarkTheme(): Flow<Boolean>
}