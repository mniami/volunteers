package android.benchmark.auth

import android.net.Uri


class SignInAuthResult(val success : Boolean,
                       val name : String,
                       val photoUrl : Uri,
                       val email : String) {
}