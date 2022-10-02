package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.remote

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeIngredients (
    val _id: String,
    val ingredients: List<String>,
    @PrimaryKey val recipe_id: String
)