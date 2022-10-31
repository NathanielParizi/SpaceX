package com.example.spacex.network

import com.example.spacex.data.LaunchResponseItem
import retrofit2.http.GET
import javax.inject.Inject

interface RetroAPIService{
    @GET("v3/launches")
    suspend fun fetchLaunches(): List<LaunchResponseItem>
}

class RetroAPI @Inject constructor(private val retroAPIService: RetroAPIService){
    suspend fun fetchLaunches() : List<LaunchResponseItem>{
        return retroAPIService.fetchLaunches()
    }
}