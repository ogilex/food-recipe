package rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.remote.IngredientsResponse
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.remote.RecipeResponse

interface RecipeService {

    @GET("search?")
    fun getPage(@Query("q")recipe: String, @Query("page")page: String): Observable<RecipeResponse>

    @GET("get")
    fun getIngredients(@Query("rId")recipe_id: String): Observable<IngredientsResponse>
}