package rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.sharedPreferences

import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.UserData

interface UserDataSource {

    fun getUserData(): UserData
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean

}