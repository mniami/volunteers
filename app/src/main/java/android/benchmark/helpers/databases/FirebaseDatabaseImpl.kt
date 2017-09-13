package android.benchmark.helpers.databases

import android.benchmark.auth.Auth
import android.benchmark.auth.SignInAuthResult
import android.benchmark.domain.User
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.DatabaseUser
import android.benchmark.helpers.dataservices.databases.IDatabaseListener
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.Executors

class FirebaseDatabaseImpl(val authentication: Auth) : Database {
    private val TAG = "FirebaseDatabaseImpl"
    private var databaseListener: IDatabaseListener? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth? = null
    private val executorService = Executors.newCachedThreadPool()
    private var authResult : AuthResult? = null
    private var user : User? = null

    override fun initAuth(){
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun signOut(){
        firebaseAuth?.signOut()
    }

    override fun addListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }

    override fun removeListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }

    override fun getUser(name: String): Observable<User> {
        return Observable.create { emitter ->
            if (user == null){
                signIn().subscribeBy(
                    onComplete = {
                        getUser(name, emitter)
                    }
                )
            }
            else {
                emitter.onNext(user!!)
            }
        }
    }

    private fun signIn() : Observable<DatabaseUser>{
        return Observable.create { emitter ->
            val credential = GoogleAuthProvider.getCredential(authentication.authUser.idToken, null)

            firebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(executorService, OnCompleteListener<AuthResult> { task ->
                Log.d(TAG, "sign in " + task.isSuccessful)

                if (task.isSuccessful) {
                    authResult = task.result
                    emitter.onNext(convert(task.result))
                }
                emitter.onComplete()
            })
        }
    }

    private fun convert(result: AuthResult): DatabaseUser {
        return DatabaseUser(
                result.user.uid,
                result.user.providerId,
                result.user.displayName,
                result.user.email,
                "")
    }

    private fun getUser(name: String, emitter: ObservableEmitter<User>){
        val ref = database.reference.child("users").child(name)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(var1: DataSnapshot) {
                val user = var1.getValue(User::class.java)
                if (user != null) {
                    this@FirebaseDatabaseImpl.user = user
                    emitter.onNext(user)
                }
                ref.removeEventListener(this)
                emitter.onComplete()
            }

            override fun onCancelled(var1: DatabaseError) {
                ref.removeEventListener(this)
                emitter.onComplete()
            }
        }
        ref.addValueEventListener(eventListener)
    }
}