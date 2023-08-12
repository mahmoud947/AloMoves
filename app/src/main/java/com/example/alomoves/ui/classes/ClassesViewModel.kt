package com.example.alomoves.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alomoves.ui.base.BaseViewModel
import com.example.core.data.Resource
import com.example.data.models.response.Classes
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _classes = MutableLiveData<Resource<Classes>>()
    val classes: LiveData<Resource<Classes>> get() = _classes

    init {
        getClasses()
    }
    private fun getClasses() {
        handleData({ repository.getClasses() }, _classes)
    }
}