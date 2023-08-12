package com.example.data.util

import androidx.lifecycle.MutableLiveData
import com.example.core.data.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.io.IOException

fun <T> errorHandler(
    resource: MutableLiveData<Resource<T>>
): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    when (throwable) {
        is HttpException -> {
            resource.postValue(Resource.Error(throwable))
        }

        is IOException -> {
            resource.postValue(Resource.ErrorWithMessage(errorMessage = "check your internet connection"))
        }
        else -> {
            resource.postValue(Resource.Error(throwable))
        }
    }

}

fun errorHandler(
    callBack: ErrorHandlerCallBack
): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()

    when (throwable) {
        is HttpException -> {
            when (throwable.code()) {
                401 -> callBack.unAuthorized(message = throwable.localizedMessage)
                400 -> callBack.notFound(message = throwable.localizedMessage)
                404 -> callBack.notFound(message = throwable.localizedMessage)
            }
        }
        is IOException -> {
            callBack.ioException(message = throwable.localizedMessage)
        }
        else -> {
            callBack.unKnownError(message = "Unknown error")
        }
    }
}

fun errorHandler(
    callBack: () -> Unit
): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    callBack.invoke()
}

interface ErrorHandlerCallBack {
    fun unAuthorized(message: String? = null) {}
    fun badRequest(message: String? = null) {}
    fun notFound(message: String? = null) {}
    fun ioException(message: String? = null) {}

    fun unKnownError(message: String? = null) {}


}
