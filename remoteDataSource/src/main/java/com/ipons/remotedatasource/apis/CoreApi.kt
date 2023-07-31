package com.ipons.remotedatasource.apis

import com.ipons.remotedatasource.dto.CreditsDto
import com.ipons.remotedatasource.dto.FullMovieDto
import com.ipons.remotedatasource.dto.ResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoreApi {

    @GET("/3/movie/popular")
    suspend fun getPopularFilms(): Response<ResultDto>

    @GET("/3/movie/top_rated")
    suspend fun getTopFilms(): Response<ResultDto>

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingFilms(): Response<ResultDto>

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingFilms(): Response<ResultDto>


    @GET("/3/search/movie")
    suspend fun searchMovie(@Query("query") searchText: String): Response<ResultDto>

    @GET("/3/movie/{movieId}")
    suspend fun getFullMovie(@Path("movieId") movieId: Int): Response<FullMovieDto>

    @GET("/3/movie/{movieId}/similar")
    suspend fun getSimilarMovie(@Path("movieId") movieId: Int): Response<ResultDto>

    @GET("/3/movie/{movieId}/credits")
    suspend fun getMovieCredits(@Path("movieId") movieId: Int): Response<CreditsDto>

}