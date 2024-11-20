package com.example.planwise1.api

import retrofit2.http.GET

interface ApiService {
    @GET("api/species-list")
    suspend fun getListPlants(): Response
}