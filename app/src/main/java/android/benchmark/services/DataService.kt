package android.benchmark.services

import android.benchmark.domain.Address
import android.benchmark.domain.Volunteer
import android.benchmark.domain.VolunteerType
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
                    description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
                    shortDescription = "This tutorial describes how to use Kotlin",
                    volunteerType = VolunteerType.Senior,
                    avatarImageUri = "https://s-media-cache-ak0.pinimg.com/564x/3b/7d/6f/3b7d6f60e2d450b899c322266fc6edfd.jpg",
                    addresses = listOf(
                            Address(
                                    "Bydgoszcz",
                                    "85-135",
                                    "Bielicka",
                                    "14",
                                    "13"))))
            volunteers.add(Volunteer(
                    name = "Kamila",
                    surname = "Grochowiecka",
                    description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
                    shortDescription = "This tutorial describes how to use Kotlin",
                    volunteerType = VolunteerType.Moderator,
                    avatarImageUri = "https://cdn4.iconfinder.com/data/icons/STROKE/communications/png/400/avatar.png",
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