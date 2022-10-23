package com.are.magicboxtwo.ui.features.detail.state

import com.are.magicboxtwo.data.model.Movie

sealed class DetailsUIStateResponse {

    object Loading : DetailsUIStateResponse()

    object Error : DetailsUIStateResponse()

    data class Success(val movie: Movie) : DetailsUIStateResponse()
}
