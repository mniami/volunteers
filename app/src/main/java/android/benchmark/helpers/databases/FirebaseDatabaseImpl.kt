package android.benchmark.helpers.databases

import android.benchmark.auth.Auth
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.IDatabaseListener
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.concurrent.TimeUnit

class FirebaseDatabaseImpl(val authentication: Auth, val TIMEOUT: Long = 10000) : Database {

    private val TAG = "FirebaseDatabaseImpl"
    private var databaseListener: IDatabaseListener? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth? = null

    override fun getCurrentUserAsync(): Observable<User> {
        return Observable.create<User>({ emitter ->
            val firebaseAuthInstance = firebaseAuth
            if (firebaseAuthInstance == null) {
                emitter.onComplete()
                return@create
            }
            val user = firebaseAuthInstance.currentUser
            if (user != null && user.uid.isNotBlank()) {
                getUserAsync(user.uid, emitter)
            } else {
                emitter.onComplete()
            }
        }).timeout(TIMEOUT, TimeUnit.MILLISECONDS)
    }

    override fun init() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }

    override fun signOut() {
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
            getUserAsync(name, emitter)
        }
    }

    private fun getUserAsync(name: String, emitter: ObservableEmitter<User>) {
        val ref = database.reference.child("users").child(name)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(var1: DataSnapshot) {
                val user = var1.getValue(User::class.java)
                if (user != null) {
                    Log.d(TAG, "user found '${user.name}'")
                    emitter.onNext(user)
                } else {
                    Log.d(TAG, "user not found")
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
//        val vref = database.reference.child("volunteers")
//        val veventListener = object : ValueEventListener {
//            override fun onDataChange(var1: DataSnapshot) {
//                for (volunteerData in var1.children) {
//                    val volunteer = var1.getValue(Volunteer::class.java)
//                    if (volunteer != null) {
//                        volunteer.toString()
//                    }
//                }
//                vref.removeEventListener(this)
//            }
//
//            override fun onCancelled(var1: DatabaseError) {
//                vref.removeEventListener(this)
//            }
//        }
//        vref.addListenerForSingleValueEvent(veventListener)
//        vref.addValueEventListener(veventListener)
    }

    override fun getVolunteers(): Observable<Volunteer> {
        val obs = Observable.create<Volunteer> { emitter ->
            val ref = database.reference.child("volunteers")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(var1: DataSnapshot) {
                    for (volunteerData in var1.children) {
                        val volunteer = volunteerData.getValue(Volunteer::class.java)
                        if (volunteer != null) {
                            emitter.onNext(volunteer)
                        }
                    }
                    ref.removeEventListener(this)
                    emitter.onComplete()
                }

                override fun onCancelled(var1: DatabaseError) {
                    ref.removeEventListener(this)
                    emitter.onComplete()
                }
            }
            ref.addListenerForSingleValueEvent(eventListener)
            ref.addValueEventListener(eventListener)
        }
        return obs.timeout(TIMEOUT, TimeUnit.MILLISECONDS)
    }
}