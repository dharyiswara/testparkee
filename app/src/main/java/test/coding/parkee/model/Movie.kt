package test.coding.parkee.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop: String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("release_date") val releaseDate: String
)
