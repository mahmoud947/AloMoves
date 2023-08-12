package com.example.data.remote

import com.example.data.models.response.Classes
import com.example.data.models.response.OverView

interface MockApi{
    suspend fun getOverView(): OverView
    suspend fun getClasses(): Classes
}