package com.example.planwise1.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("species-list")
    suspend fun getSpeciesList(
        @Query("key") apiKey: String,
        @Query("page") page: Int = 1
    ): Response
}