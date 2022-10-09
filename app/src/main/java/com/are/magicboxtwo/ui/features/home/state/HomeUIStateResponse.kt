package com.are.magicboxtwo.ui.features.home.state

import com.are.magicboxtwo.data.model.Movie

sealed class HomeUIStateResponse {

    object Loading : HomeUIStateResponse()

    object Error : HomeUIStateResponse()

    data class Success(val movieList: List<Movie>) : HomeUIStateResponse()
}
