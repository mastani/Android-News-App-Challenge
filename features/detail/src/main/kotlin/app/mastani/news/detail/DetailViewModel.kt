package app.mastani.news.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import app.mastani.news.common.onFailure
import app.mastani.news.common.onSuccess
import app.mastani.news.domain.model.NewsArticle
import app.mastani.news.domain.usecase.AddArticleToArchiveUseCase
import app.mastani.news.domain.usecase.GetIsArticleArchivedUseCase
import app.mastani.news.domain.usecase.RemoveArticleFromArchiveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addArticleToArchiveUseCase: AddArticleToArchiveUseCase,
    private val getIsArticleArchivedUseCase: GetIsArticleArchivedUseCase,
    private val removeArticleFromArchiveUseCase: RemoveArticleFromArchiveUseCase
) : ViewModel() {

    val article = savedStateHandle.toRoute<NewsArticle>()

    private val _isArchived = MutableStateFlow(false)
    val isArchived: StateFlow<Boolean> = _isArchived

    init {
        getIsArchiveArticle()
    }

    fun onIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.AddArticleToArchive -> addArticleToArchive(intent.newsArticle)
            is DetailIntent.RemoveArticleFromArchive -> removeArticleFromArchive(intent.newsArticle)
        }
    }

    private fun addArticleToArchive(newsArticle: NewsArticle) {
        viewModelScope.launch {
            addArticleToArchiveUseCase(newsArticle)
                .onSuccess {
                    _isArchived.emit(true)
                }.onFailure { error ->
                    _isArchived.emit(false)
                }
        }
    }

    private fun removeArticleFromArchive(newsArticle: NewsArticle) {
        viewModelScope.launch {
            removeArticleFromArchiveUseCase(newsArticle.title)
                .onSuccess {
                    _isArchived.emit(false)
                }.onFailure { error ->
                    _isArchived.emit(true)
                }
        }
    }

    private fun getIsArchiveArticle() {
        viewModelScope.launch {
            getIsArticleArchivedUseCase(article.title)
                .onSuccess { isArchived ->
                    _isArchived.emit(isArchived)
                }.onFailure { error ->
                    _isArchived.emit(false)
                }
        }
    }
}