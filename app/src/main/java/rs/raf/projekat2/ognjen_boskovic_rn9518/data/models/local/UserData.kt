package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local

data class UserData (
    val username: String,
    val pin: Int
)

fun UserData.toUser(): User {
    return User(
        username
    )
}