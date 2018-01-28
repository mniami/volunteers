package android.benchmark.helpers.databases.actions

import android.benchmark.domain.Volunteer
import io.reactivex.Observable

interface GetVolunteersAction {
    fun getVolunteers(): Observable<Volunteer>
}