package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe (
    val image_url: String,
    val _id: String,
    val recipe_id: String,
    val social_rank: String,
    val publisher: String,
    val title: String
)