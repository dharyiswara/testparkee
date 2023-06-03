package test.coding.parkee.network

import retrofit2.http.GET
import retrofit2.http.Path
import test.coding.parkee.model.GetReview
import test.coding.parkee.model.ListMovie
import test.coding.parkee.model.Movie

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovieList(): ListMovie?

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(): ListMovie?

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovieList(): ListMovie?

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Int): Movie?

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewMovie(@Path("movie_id") movieId: Int): GetReview?

}