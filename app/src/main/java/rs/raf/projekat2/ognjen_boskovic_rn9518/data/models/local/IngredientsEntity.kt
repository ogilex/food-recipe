package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
data class IngredientsEntity (
    @PrimaryKey val id: String,
    val ingredients: List<String>,
    val recipe_id: String
)