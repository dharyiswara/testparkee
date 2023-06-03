package test.coding.parkee.model

import com.google.gson.annotations.SerializedName

data class GetReview(@SerializedName("results") val results: List<Review>?)