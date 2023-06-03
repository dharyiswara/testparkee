package test.coding.parkee.datasource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.coding.parkee.model.GetReview
import test.coding.parkee.model.Movie
import test.coding.parkee.network.ApiService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieDetailDataSource @Inject constructor(
    private val apiService: ApiService
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getDetailMovie(movieId: Int): Movie? {
        return withContext(coroutineContext) {
            try {
                apiService.getDetailMovie(movieId)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getReviewMovie(movieId: Int): GetReview? {
        return withContext(coroutineContext) {
            try {
                apiService.getReviewMovie(movieId)
            } catch (e: Exception) {
                null
            }
        }
    }

}