package test.coding.parkee.repository

import test.coding.parkee.database.FavoriteMovieDatabase
import test.coding.parkee.datasource.MovieDetailDataSource
import test.coding.parkee.model.FavoriteTable
import test.coding.parkee.model.GetReview
import test.coding.parkee.model.Movie
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource,
    private val favoriteDatabase: FavoriteMovieDatabase
) {

    suspend fun getDetailMovie(movieId: Int): Movie? = movieDetailDataSource.getDetailMovie(movieId)

    suspend fun getReviewMovie(movieId: Int): GetReview? =
        movieDetailDataSource.getReviewMovie(movieId)

    suspend fun getFavoriteMovie(movieId: Int): FavoriteTable? {
        return favoriteDatabase.favoriteMovieDao().getSelectedFavoriteMovie(movieId).getOrNull(0)
    }

    suspend fun insertFavoriteMovie(favoriteTable: FavoriteTable) {
        favoriteDatabase.favoriteMovieDao().insertFavorite(favoriteTable)
    }

    suspend fun deleteFavoriteMovie(movieId: Int) {
        favoriteDatabase.favoriteMovieDao().deleteFavorite(movieId)
    }

}