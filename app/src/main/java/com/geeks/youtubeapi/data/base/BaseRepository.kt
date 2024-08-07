package com.geeks.youtubeapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geeks.youtubeapi.data.repository.ARG_ERROR_MESSAGE
import com.geeks.youtubeapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    fun <T> sendRequest(
        requestFun: suspend () -> Response<T>
    ) :LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        try {
            val response = requestFun()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: run {
                    emit(Resource.Error("Пустое тело ответа"))
                }
            } else {
                emit(Resource.Error("Ошибка: ${response.code()} ${response.message()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
        }
    }
    protected fun <T> doRequest(request: suspend () -> T): LiveData<Resource<T>> = liveData(
        Dispatchers.IO
    ) {
        emit(Resource.Loading())
        try {
            val response = request()
            if (response != null) {
                emit(Resource.Success(response))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

}