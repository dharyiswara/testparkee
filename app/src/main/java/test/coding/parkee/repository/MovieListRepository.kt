package test.coding.parkee.repository

import test.coding.parkee.datasource.MovieListDataSource
import test.coding.parkee.model.ListMovie
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieListDataSource: MovieListDataSource
) {

    suspend fun getPopularMovieList(): ListMovie? = movieListDataSource.getPopularMovie()

    suspend fun getTopRatedMovieList(): ListMovie? = movieListDataSource.getTopRatedMovieList()

    suspend fun getNowPlayingMovieList(): ListMovie? = movieListDataSource.getNowPlayingMovieList()

}