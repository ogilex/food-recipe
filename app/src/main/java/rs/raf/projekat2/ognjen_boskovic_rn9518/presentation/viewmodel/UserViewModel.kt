package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.User
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.users.UserRepository
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.UserContract

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel(), UserContract.ViewModel {

    override val user: MutableLiveData<User> = MutableLiveData()

    override fun getUser() {
        user.value = userRepository.getUser()
    }

    override fun login(username: String, pin: Int): Boolean {
        return userRepository.login(username, pin)
    }

    override fun isLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }

}