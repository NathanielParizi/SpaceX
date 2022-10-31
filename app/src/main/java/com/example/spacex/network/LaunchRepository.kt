package com.example.spacex.network

import com.example.spacex.data.LaunchResponseItem
import javax.inject.Inject

interface LaunchRepository{
    suspend fun fetchLaunches():List<LaunchResponseItem>
}

class LaunchRepositoryImpl @Inject constructor(retroAPIService: RetroAPIService):LaunchRepository {
    private val retroAPI: RetroAPI = RetroAPI(retroAPIService)

    override suspend fun fetchLaunches(): List<LaunchResponseItem> {
        return retroAPI.fetchLaunches()
    }

}