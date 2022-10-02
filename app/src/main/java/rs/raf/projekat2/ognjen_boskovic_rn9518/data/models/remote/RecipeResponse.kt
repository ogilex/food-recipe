package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val count: String,
    val recipes: List<Recipe>
)