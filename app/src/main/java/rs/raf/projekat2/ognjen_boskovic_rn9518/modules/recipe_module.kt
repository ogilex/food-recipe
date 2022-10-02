package rs.raf.projekat2.ognjen_boskovic_rn9518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.RecipeDB
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.remote.RecipeService
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.recipes.RecipeRepository
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.recipes.RecipeRepositoryImpl
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.RecipeViewModel

val recipeModule = module {

    viewModel { RecipeViewModel(recipeRepository = get()) }

    single<RecipeRepository> { RecipeRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<RecipeDB>().getRecipeDao() }

    single<RecipeService> { create(retrofit = get()) }

}