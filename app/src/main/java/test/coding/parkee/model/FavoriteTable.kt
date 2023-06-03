package test.coding.parkee.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "favorite_movie",
    primaryKeys = ["id"]
)
data class FavoriteTable(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("poster") val poster: String
)