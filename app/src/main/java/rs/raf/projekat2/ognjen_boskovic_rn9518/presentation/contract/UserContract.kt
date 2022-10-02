package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.User

interface UserContract {

    interface ViewModel {
        val user: LiveData<User>
        fun getUser()
        fun login(username: String, pin: Int): Boolean
        fun isLoggedIn(): Boolean
    }
}