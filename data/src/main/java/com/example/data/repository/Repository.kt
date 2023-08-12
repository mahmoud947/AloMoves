package com.example.data.repository

import com.example.data.models.response.OverView

interface Repository {
    suspend fun getDemoRes(): OverView
}