package app.mastani.news.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.news.common.onFailure
import app.mastani.news.common.onSuccess
import app.mastani.news.domain.usecase.GetCachedArticleUseCase
import app.mastani.news.domain.usecase.GetHeadlinesUseCase
import app.mastani.news.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHeadlinesUseCase: GetHeadlinesUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getCachedArticleUseCase: GetCachedArticleUseCase
) : ViewModel() {

    private val _headlineState = MutableStateFlow<HeadlinesState>(HeadlinesState.Loading)
    val headlineState: StateFlow<HeadlinesState> = _headlineState

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newsState

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.FetchHeadlines -> fetchHeadlines()
            is HomeIntent.FetchNews -> fetchNews(intent.query)
        }
    }

    private fun fetchCachedArticles() {
        viewModelScope.launch {
            _newsState.emit(NewsState.Loading)

            getCachedArticleUseCase()
                .onSuccess { newsArticles ->
                    _newsState.emit(
                        NewsState.Success(
                            articles = newsArticles,
                            isFromCache = true
                        )
                    )
                }.onFailure { error ->
                    // Retrieving from cache failed.
                }
        }
    }

    private fun fetchHeadlines() {
        viewModelScope.launch {
            _headlineState.emit(HeadlinesState.Loading)

            getHeadlinesUseCase()
                .onSuccess { newsArticles ->
                    _headlineState.emit(
                        HeadlinesState.Success(articles = newsArticles)
                    )
                }.onFailure { error ->
                    _headlineState.emit(
                        HeadlinesState.Error(error = error.message)
                    )
                }
        }
    }

    private fun fetchNews(query: String) {
        viewModelScope.launch {
            _newsState.emit(NewsState.Loading)

            getNewsUseCase(
                query = query,
                shouldCache = true
            )
                .onSuccess { newsArticles ->
                    _newsState.emit(
                        NewsState.Success(
                            articles = newsArticles,
                            isFromCache = false
                        )
                    )
                }.onFailure { error ->
                    _newsState.emit(
                        NewsState.Error(error = error.message)
                    )

                    // fetched cached news from database.
                    fetchCachedArticles()
                }
        }
    }
}