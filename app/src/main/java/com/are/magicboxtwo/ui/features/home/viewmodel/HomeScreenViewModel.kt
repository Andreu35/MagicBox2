package com.are.magicboxtwo.ui.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.are.magicboxtwo.data.remote.Resource
import com.are.magicboxtwo.data.repository.MagicBoxRepository
import com.are.magicboxtwo.ui.common.topappbar.SearchState
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.features.home.state.HomeUIStateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MagicBoxRepository
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        _movieListState.value = HomeUIStateResponse.Error
        exception.printStackTrace()
    }

    private val _movieListState = mutableStateOf<HomeUIStateResponse>(HomeUIStateResponse.Success(listOf()))
    val movieListState: State<HomeUIStateResponse> = _movieListState

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun searchMovies(query: String) {
        viewModelScope.launch(errorHandler) {
            _movieListState.value = HomeUIStateResponse.Loading

            val result = repository.searchMovies(query)

            if (result.value?.status == Resource.Status.SUCCESS) {
                _movieListState.value = HomeUIStateResponse.Success(result.value?.data?.results!!)
            } else {
                _movieListState.value = HomeUIStateResponse.Error
            }
            _uiState.update { currentState ->
                currentState.copy(
                    lastQuery = query
                )
            }
        }
    }

    fun tryAgain() {
        searchMovies(_uiState.value.lastQuery)
    }

    fun updateAppBarTitle(title: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    appBarTitle = title
                )
            }
        }
    }

    fun openOrCloseSearchBar(state: SearchState) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    searchState = state
                )
            }
        }
    }
}