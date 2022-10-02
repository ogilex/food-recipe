package rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.users

import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.sharedPreferences.UserDataSource
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.User
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.toUser

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {

    override fun getUser(): User {
        return userDataSource.getUserData().toUser()
    }

    override fun login(username: String, pin: Int): Boolean {
        return userDataSource.login(username, pin)
    }

    override fun isLoggedIn(): Boolean {
        return userDataSource.isLoggedIn()
    }


}