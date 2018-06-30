package android.benchmark.auth

class AuthUser (val name : String,
                val photoUrl : String,
                val email : String,
                val id : String,
                val idToken : String) {
    companion object {
        fun createEmpty() : AuthUser {
            return AuthUser("Janek","","janek@u.jana.pl", "0000", "0000")
        }
    }
    fun isEmpty() : Boolean {
        return id == "0000"
    }
}