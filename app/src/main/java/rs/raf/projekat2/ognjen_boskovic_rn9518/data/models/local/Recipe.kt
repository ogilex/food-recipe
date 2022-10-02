package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local

import java.io.Serializable
import java.util.*


data class Recipe (

    val id: String,
    val page: String = "",
    val title: String,
    val imageUrl: String = "",
    val socialRank: String = "",
    val publisher: String = "",
    val recipe_id: String,
    val ingredients: List<String>,
    val category: String = "",
    val date: Date = Date()

): Serializable