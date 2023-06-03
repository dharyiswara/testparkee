package test.coding.parkee.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("content") val content: String
)
