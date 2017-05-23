package android.benchmark.ui.fragments.settings

class AuthenticationPresenter {
    companion object {
        val NOT_SIGNED_UP = 1
        val NOT_SIGNED_IN = 2
        val SIGNED_IN = 3
        val UNKNOWN_STATE = 0
    }
    var state = UNKNOWN_STATE

    fun needsToSignUp() = state == NOT_SIGNED_UP

    fun needsToSignIn() = state == NOT_SIGNED_IN

    fun signedIn() = state == SIGNED_IN
}