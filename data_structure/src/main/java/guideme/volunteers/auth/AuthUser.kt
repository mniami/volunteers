package guideme.volunteers.auth

class AuthUser (val name : String,
                val photoUrl : String,
                val email : String,
                val id : String,
                val idToken : String) {
    companion object {
        fun createEmpty(): AuthUser = AuthUser("", "", "", "", "")
    }

    fun isEmpty(): Boolean = idToken.isEmpty()
}