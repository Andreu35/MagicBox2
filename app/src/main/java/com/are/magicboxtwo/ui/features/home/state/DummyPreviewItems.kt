package com.are.magicboxtwo.ui.features.home.state

import com.are.magicboxtwo.data.model.Movie

fun dummyPreviewItems(): List<Movie> {
    return listOf(
        Movie(
            id = 0,
            title = "Movie 1"
        ),
        Movie(
            id = 1,
            title = "Movie 2"
        ),
        Movie(
            id = 2,
            title = "Movie 3"
        ),
        Movie(
            id = 3,
            title = "Movie 4"
        ),
        Movie(
            id = 4,
            title = "Movie 5"
        ),
        Movie(
            id = 5,
            title = "Movie 6"
        )
    )
}