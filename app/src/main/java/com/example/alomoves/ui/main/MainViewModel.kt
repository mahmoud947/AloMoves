package com.example.alomoves.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alomoves.ui.base.BaseViewModel
import com.example.core.data.Resource
import com.example.data.models.response.OverView
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _overView = MutableLiveData<Resource<OverView>>()
    val overView: LiveData<Resource<OverView>> get() = _overView
    fun getOverView() {
        viewModelScope.launch(Dispatchers.IO) {
            _overView.postValue(Resource.Loading)
            try {
                val response = repository.getOverView()
                _overView.postValue(Resource.Success(response))
            } catch (e: Exception) {
                _overView.postValue(Resource.Error(e))
            }
        }
    }
}