package rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.converters.DateConverter
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.converters.StringListConverter
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.IngredientsEntity
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.RecipeEntity
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.SavedRecipeEntity

@Database(
    entities = [RecipeEntity::class, IngredientsEntity::class, SavedRecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class RecipeDB : RoomDatabase(){
    abstract fun getRecipeDao(): RecipeDao
}