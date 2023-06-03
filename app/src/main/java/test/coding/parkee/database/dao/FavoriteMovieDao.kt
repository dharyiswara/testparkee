package test.coding.parkee.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import test.coding.parkee.model.FavoriteTable

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movie")
    suspend fun getFavoriteMovie(): List<FavoriteTable>

    @Query("SELECT * FROM favorite_movie WHERE id = :movieId")
    suspend fun getSelectedFavoriteMovie(movieId: Int): List<FavoriteTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteTable)

    @Query("DELETE FROM favorite_movie WHERE id = :movieId")
    suspend fun deleteFavorite(movieId: Int)

}