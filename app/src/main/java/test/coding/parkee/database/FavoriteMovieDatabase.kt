package test.coding.parkee.database

import androidx.room.Database
import androidx.room.RoomDatabase
import test.coding.parkee.database.dao.FavoriteMovieDao
import test.coding.parkee.model.FavoriteTable

@Database(
    entities = [FavoriteTable::class],
    version = 2,
    exportSchema = false
)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMovieDao

}