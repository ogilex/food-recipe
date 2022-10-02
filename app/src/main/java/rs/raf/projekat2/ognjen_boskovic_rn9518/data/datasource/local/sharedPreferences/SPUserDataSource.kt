package rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.sharedPreferences

import android.content.SharedPreferences
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.UserData

class SPUserDataSource (
    private val sharedPreferences: SharedPreferences
) : UserDataSource {

    companion object {
        const val USER_LOGGED_IN_KEY = "UserLoggedIn"
        const val USER_USERNAME_KEY = "UserUsernameKey"
        const val USER_PIN_KEY = "UserPinKey"
    }

    init {
        val editor = sharedPreferences.edit()
        editor.putString(USER_USERNAME_KEY, "user")
        editor.putInt(USER_PIN_KEY, 123)
        editor.apply()
    }
    override fun getUserData(): UserData {
        val username = sharedPreferences.getString(USER_USERNAME_KEY, "") ?: ""
        val pin = sharedPreferences.getInt(USER_PIN_KEY, 0)
        return UserData(
            username,
            pin
        )
    }

    override fun login(username: String, pin: Int): Boolean {
        val SpUsername = sharedPreferences.getString(USER_USERNAME_KEY, "") ?: ""
        val spPin = sharedPreferences.getInt(USER_PIN_KEY, 0)
        if (username == SpUsername && pin == spPin) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(USER_LOGGED_IN_KEY, true)
            editor.apply()
            return true
        };
        return false;
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(USER_LOGGED_IN_KEY, false)
    }
}