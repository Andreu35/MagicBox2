package com.are.magicboxtwo.ui.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.are.magicboxtwo.data.remote.Resource
import com.are.magicboxtwo.data.repository.MagicBoxRepository
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.features.home.state.HomeUIStateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
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

    private val _movieListState = mutableStateOf<HomeUIStateResponse>(
        HomeUIStateResponse.Success(
            listOf()
        )
    )
    val movieListState: State<HomeUIStateResponse>
        get() = _movieListState

    val uiState: HomeUIState by mutableStateOf(HomeUIState())

    fun tryAgain() {
        viewModelScope.launch { }
    }

    fun getMoviesByPage(query: String, page: Int) {
        viewModelScope.launch(errorHandler) {
            _movieListState.value = HomeUIStateResponse.Loading

            val result = repository.searchMoviesWithPage(query, page)

            if (result.value?.status == Resource.Status.SUCCESS) {
                _movieListState.value = HomeUIStateResponse.Success(result.value?.data?.results!!)
            } else {
                _movieListState.value = HomeUIStateResponse.Error
            }
        }
    }

    fun searchMovies() {

    }
}