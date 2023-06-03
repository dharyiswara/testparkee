package test.coding.parkee.datasource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.coding.parkee.model.ListMovie
import test.coding.parkee.network.ApiService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieListDataSource @Inject constructor(
    private val apiService: ApiService
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getPopularMovie(): ListMovie? {
        return withContext(coroutineContext) {
            try {
                apiService.getPopularMovieList()
            } catch (e: Exception) {
                ListMovie(1, listOf())
            }
        }
    }

    suspend fun getTopRatedMovieList(): ListMovie? {
        return withContext(coroutineContext) {
            try {
                apiService.getTopRatedMovieList()
            } catch (e: Exception) {
                ListMovie(1, listOf())
            }
        }
    }

    suspend fun getNowPlayingMovieList(): ListMovie? {
        return withContext(coroutineContext) {
            try {
                apiService.getNowPlayingMovieList()
            } catch (e: Exception) {
                ListMovie(1, listOf())
            }
        }
    }

}