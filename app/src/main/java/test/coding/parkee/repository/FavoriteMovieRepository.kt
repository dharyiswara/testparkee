package test.coding.parkee.repository

import test.coding.parkee.database.FavoriteMovieDatabase
import test.coding.parkee.model.FavoriteTable
import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(
    private val favoriteDatabase: FavoriteMovieDatabase
) {

    suspend fun getFavoriteMovie(): List<FavoriteTable> {
        return favoriteDatabase.favoriteMovieDao().getFavoriteMovie()
    }

}