package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "savedRecipes")
data class SavedRecipeEntity (
    @PrimaryKey(autoGenerate = true) val room_id: Int,
    val id: String,
    val title: String,
    val publisher: String,
    val recipe_id: String,
    val category: String,
    val date: Date,
    val imageUrl: String,
    val socialRank: String
)
