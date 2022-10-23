package com.are.magicboxtwo.ui.features.home.state

import com.are.magicboxtwo.ui.common.topappbar.SearchState

data class HomeUIState(
    val appBarTitle: String = "",
    val searchState: SearchState = SearchState.Closed,
    val lastQuery: String = ""
)
