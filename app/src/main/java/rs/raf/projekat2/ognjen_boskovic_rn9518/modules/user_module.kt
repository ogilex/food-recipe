package rs.raf.projekat2.ognjen_boskovic_rn9518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.sharedPreferences.SPUserDataSource
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.sharedPreferences.UserDataSource
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.users.UserRepository
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.users.UserRepositoryImpl
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel { UserViewModel(userRepository = get ()) }

    single <UserRepository> { UserRepositoryImpl(userDataSource = get(named("shared"))) }

    single<UserDataSource>(named("shared")) { SPUserDataSource(sharedPreferences = get()) }
}