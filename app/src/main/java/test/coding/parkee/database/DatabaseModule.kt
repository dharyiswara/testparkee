package test.coding.parkee.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private var favoriteDatabase: FavoriteMovieDatabase? = null

    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteMovieDatabase {
        val database = favoriteDatabase ?: buildDatabase(context)
        if(favoriteDatabase == null) {
            favoriteDatabase = buildDatabase(context)
        }
        return database
    }

    private fun buildDatabase(context: Context): FavoriteMovieDatabase {
        return Room.databaseBuilder(context, FavoriteMovieDatabase::class.java, "favorite.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}