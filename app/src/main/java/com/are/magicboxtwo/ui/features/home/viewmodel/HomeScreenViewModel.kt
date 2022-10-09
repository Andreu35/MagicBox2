package com.are.magicboxtwo.ui.features.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.are.magicboxtwo.data.repository.MagicBoxRepository
import com.are.magicboxtwo.ui.features.home.state.HomeUIState
import com.are.magicboxtwo.ui.features.home.state.HomeUIStateResponse
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val repository: MagicBoxRepository
): ViewModel() {

    private val _movieListState = mutableStateOf<HomeUIStateResponse>(HomeUIStateResponse.Loading)
    val movieListState: State<HomeUIStateResponse>
        get() = _movieListState

    val uiState: HomeUIState by mutableStateOf(HomeUIState())
}