package test.coding.parkee.model

import com.google.gson.annotations.SerializedName

data class ListMovie(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movieList: List<Movie>
)
