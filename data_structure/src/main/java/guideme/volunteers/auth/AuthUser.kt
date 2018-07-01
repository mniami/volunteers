package guideme.volunteers.auth

class AuthUser (val name : String,
                val photoUrl : String,
                val email : String,
                val id : String,
                val idToken : String) {
    companion object {
        fun createEmpty() : AuthUser {
            return AuthUser("","","", "", "")
        }
    }
    fun isEmpty() : Boolean {
        return id.isEmpty()
    }
}