package com.example.data.repository

import com.example.core.data.BaseRepository
import com.example.data.models.response.OverView
import com.example.data.remote.MockApi


class RepositoryImp(
    private val api: MockApi,
) :Repository {

    override suspend fun getDemoRes(): OverView = api.getOverView()
}