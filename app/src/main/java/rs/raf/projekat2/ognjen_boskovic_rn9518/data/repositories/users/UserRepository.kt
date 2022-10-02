package rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.users

import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.User

interface UserRepository {
    fun getUser(): User
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean
}