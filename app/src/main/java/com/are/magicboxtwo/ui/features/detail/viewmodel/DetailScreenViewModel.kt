package com.are.magicboxtwo.ui.features.detail.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.are.magicboxtwo.data.model.Movie
import com.are.magicboxtwo.data.remote.Resource
import com.are.magicboxtwo.data.repository.MagicBoxRepository
import com.are.magicboxtwo.ui.features.detail.state.DetailsUIStateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: MagicBoxRepository
) : ViewModel() {

    private val _movieState = mutableStateOf<DetailsUIStateResponse>(
        DetailsUIStateResponse.Success(
            Movie()
        )
    )
    val movieState: State<DetailsUIStateResponse> = _movieState

    private val movieIdState = mutableStateOf(0)

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        _movieState.value = DetailsUIStateResponse.Error
        exception.printStackTrace()
    }

    fun getMovieById(movieId: Int) {
        viewModelScope.launch(errorHandler) {
            _movieState.value = DetailsUIStateResponse.Loading

            val result = repository.getMovie(movieId)

            if (result.value?.status == Resource.Status.SUCCESS) {
                _movieState.value = DetailsUIStateResponse.Success(result.value?.data!!)
            } else {
                _movieState.value = DetailsUIStateResponse.Error
            }
            movieIdState.value = movieId
        }
    }

    fun tryAgain() {
        getMovieById(movieIdState.value)
    }
}