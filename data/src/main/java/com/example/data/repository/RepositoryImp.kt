package com.example.data.repository

import com.example.core.data.BaseRepository
import com.example.data.models.response.Classes
import com.example.data.models.response.OverView
import com.example.data.remote.MockApi


class RepositoryImp(
    private val api: MockApi,
) :Repository {

    override suspend fun getOverView(): OverView = api.getOverView()
    override suspend fun getClasses(): Classes =api.getClasses()
}