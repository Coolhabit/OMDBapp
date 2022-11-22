package com.coolhabit.filmsearchapp.data.network

import com.coolhabit.filmsearchapp.data.network.entities.NetworkMovieDetails
import com.coolhabit.filmsearchapp.data.network.entities.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {

    @GET("/")
    suspend fun getMovies(
        @Query("s") query: String?
    ): NetworkResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") movieId: String?
    ): NetworkMovieDetails
}
