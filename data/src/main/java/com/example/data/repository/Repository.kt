package com.example.data.repository

import com.example.data.models.response.Classes
import com.example.data.models.response.OverView

interface Repository {
    suspend fun getOverView(): OverView
    suspend fun getClasses(): Classes
}