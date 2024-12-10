package app.mastani.news.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import app.mastani.news.data.datasource.local.datastore.IS_DARK_THEME_KEY
import app.mastani.news.data.datasource.local.datastore.appConfigDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(private val context: Context) : AppConfigRepository {

    override suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        context.appConfigDataStore.edit { preferences ->
            preferences[IS_DARK_THEME_KEY] = isDarkTheme
        }
    }

    override fun getIsDarkTheme(): Flow<Boolean> {
        return context.appConfigDataStore.data
            .map { preferences ->
                preferences[IS_DARK_THEME_KEY] ?: false // Default to light theme
            }
    }
}