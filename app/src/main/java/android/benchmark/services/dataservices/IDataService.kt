package android.benchmark.services.dataservices

import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import io.reactivex.Observable

interface IDataService {
    fun getVolunteers(): Observable<List<Volunteer>>
    fun getUser(): Observable<User>
}