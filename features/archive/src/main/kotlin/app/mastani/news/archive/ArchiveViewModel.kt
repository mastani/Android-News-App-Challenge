package app.mastani.news.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.news.common.onFailure
import app.mastani.news.common.onSuccess
import app.mastani.news.domain.usecase.GetArchiveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val getArchiveArticleUseCase: GetArchiveArticleUseCase
) : ViewModel() {

    private val _archiveState = MutableStateFlow<ArchiveState>(ArchiveState.Loading)
    val archiveState: StateFlow<ArchiveState> = _archiveState

    fun onIntent(intent: ArchiveIntent) {
        when (intent) {
            ArchiveIntent.FetchArchivedArticles -> fetchArchivedArticles()
        }
    }

    private fun fetchArchivedArticles() {
        viewModelScope.launch {
            _archiveState.emit(ArchiveState.Loading)

            getArchiveArticleUseCase()
                .onSuccess { newsArticles ->
                    _archiveState.emit(
                        ArchiveState.Success(articles = newsArticles)
                    )
                }.onFailure { error ->
                    _archiveState.emit(
                        ArchiveState.Error(error = error.message)
                    )
                }
        }
    }
}