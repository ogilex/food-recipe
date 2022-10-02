package rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local

sealed class Resource <out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val message: String = "") : Resource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): Resource<T>()
}