package com.example.alomoves.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.data.util.ErrorHandlerCallBack
import com.example.data.util.errorHandler
import com.example.startupproject.ui.base.util.ErrorModel
import com.example.startupproject.ui.base.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {


    open fun <T> handleData(
        filterCriteria: suspend () -> T,
        data: MutableLiveData<Resource<T>>
    ) {
        data.value = Resource.Loading
        viewModelScope.launch(Dispatchers.IO + errorHandler(data)) {
            val result = filterCriteria.invoke()
            data.postValue(Resource.Success(result))
        }
    }


    open fun <T> baseHandleData(
        filterCriteria: suspend () -> T,
        data: MutableLiveData<T>
    ) {
        showLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + errorHandler(BaseErrorHandlerCallBack())) {
            val result = filterCriteria.invoke()
            data.postValue(result)
            showLoading.postValue(false)
        }
    }

    open fun handelBackButtonPress() {
        navigationCommand.value = NavigationCommand.Back
    }

    open inner class BaseErrorHandlerCallBack : ErrorHandlerCallBack {
        override fun unAuthorized(message: String?) {
            showErrorMessage.postValue(ErrorModel("UnAuthorized", "retry"))
        }

        override fun badRequest(message: String?) {
            showErrorMessage.postValue(ErrorModel("Bad Request", "retry"))
        }

        override fun notFound(message: String?) {
            showErrorMessage.postValue(ErrorModel("not Found", "retry"))
        }

        override fun ioException(message: String?) {
            showErrorMessage.postValue(ErrorModel("Check your internet connection", "retry"))
        }

        override fun unKnownError(message: String?) {
            showErrorMessage.postValue(ErrorModel("Unknown error", "retry"))
        }
    }


    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val showErrorMessage: SingleLiveEvent<ErrorModel?> = SingleLiveEvent()
    val showSnackBar: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBarInt: SingleLiveEvent<Int> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoData: MutableLiveData<Boolean> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
    }
}