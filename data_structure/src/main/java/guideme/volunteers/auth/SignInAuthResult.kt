package guideme.volunteers.auth

class SignInAuthResult(val success : Boolean,
                       val authUser : AuthUser){
    companion object {
        fun createEmpty() : SignInAuthResult{
            return SignInAuthResult(false, AuthUser.createEmpty())
        }
    }
}