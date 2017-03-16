package android.benchmark.services

import android.benchmark.domain.Address
import android.benchmark.domain.Volunteer
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

interface IDataService {
    fun getVolunteers(): Observable<List<Volunteer>>
}

class DataService : IDataService {
    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.create { emitter: ObservableEmitter<List<Volunteer>> ->
            val volunteers = arrayListOf<Volunteer>()
            volunteers.add(Volunteer(name = "Damian"))
            emitter.onNext(volunteers)
        }
    }
}

class DataServiceMock : IDataService {
    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.create { emitter: ObservableEmitter<List<Volunteer>> ->
            val volunteers = arrayListOf<Volunteer>()
            volunteers.add(Volunteer(
                    name = "Damian",
                    surname = "Szczepański",
                    addresses = listOf(
                            Address(
                                    "Bydgoszcz",
                                    "85-135",
                                    "Bielicka",
                                    "14",
                                    "13"))))
            volunteers.add(Volunteer(
                    name = "Bożena",
                    surname = "Szczepańska",
                    addresses = listOf(
                            Address(
                                    "Iława",
                                    "14-200",
                                    "1 Maja",
                                    "35A",
                                    "7"))))
            emitter.onNext(volunteers)
        }
    }
}