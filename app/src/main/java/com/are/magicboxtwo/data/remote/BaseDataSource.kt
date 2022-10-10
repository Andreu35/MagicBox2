package com.are.magicboxtwo.data.remote

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): MutableLiveData<Resource<T>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.Status.SUCCESS
                    return Resource.success(body)
                }
            }
            Resource.Status.ERROR
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): MutableLiveData<Resource<T>> {
        Resource.Status.ERROR
        return Resource.error("Network call has failed for a following reason: $message")
    }

}