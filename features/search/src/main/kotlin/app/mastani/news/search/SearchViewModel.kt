package app.mastani.news.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mastani.news.common.onFailure
import app.mastani.news.common.onSuccess
import app.mastani.news.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _userQuery: MutableStateFlow<String> = MutableStateFlow("")

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState

    fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchNews -> {
                viewModelScope.launch {
                    _userQuery.emit(intent.query)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _userQuery.debounce(500).collectLatest { input ->
                if (input.isEmpty()) return@collectLatest
                searchNews(input)
            }
        }
    }

    private fun searchNews(query: String) {
        viewModelScope.launch {
            _searchState.emit(SearchState.Loading)

            getNewsUseCase(
                query = query,
                shouldCache = false
            )
                .onSuccess { newsArticles ->
                    _searchState.emit(
                        SearchState.Success(articles = newsArticles)
                    )
                }.onFailure { error ->
                    _searchState.emit(
                        SearchState.Error(error = error.message)
                    )
                }
        }
    }
}