package com.are.magicboxtwo.data.remote

import android.content.Context
import com.are.magicboxtwo.R
import com.are.magicboxtwo.data.remote.net.MagicBoxAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MagicBoxDataSource @Inject constructor(
    private val api: MagicBoxAPI,
    context: Context
) : BaseDataSource() {

    private val key = context.getString(R.string.api_key)

    suspend fun getMovie(id: Int) = getResult {
        withContext(Dispatchers.IO) {
            api.getMovie(id, key)
        }
    }

    suspend fun searchMoviesWithPage(query: String, page: Int) = getResult {
        withContext(Dispatchers.IO) {
            api.getSearchMoviesWithPage(key, query, page)
        }
    }

    suspend fun searchMovies(query: String) = getResult {
        withContext(Dispatchers.IO) {
            api.getSearchMovies(key, query)
        }
    }
}