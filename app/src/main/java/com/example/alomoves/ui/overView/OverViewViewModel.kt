package com.example.alomoves.ui.overView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alomoves.ui.base.BaseViewModel
import com.example.core.data.Resource
import com.example.data.models.response.OverView
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverViewViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _overView = MutableLiveData<Resource<OverView>>()
    val overView: LiveData<Resource<OverView>> get() = _overView

    init {
        getOverViewData()
    }
    private fun getOverViewData() {
        handleData({ repository.getOverView() }, _overView)
    }

}