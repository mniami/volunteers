package android.benchmark.services.dataservices

import android.benchmark.domain.*
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class DataServiceMock : DataService {
    override fun getUser(): Observable<User> {
        return Observable.create { emitter: ObservableEmitter<User> ->
            emitter.onNext(User(name = "damian.szczepanski", surname = "Damian Szczepanski"))
            emitter.onComplete()
        }
    }

    val DESCRIPTION = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View"

    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.create { emitter: ObservableEmitter<List<Volunteer>> ->
            val volunteers = arrayListOf<Volunteer>()
            val projectImages = listOf(
                    ImageMetadata("", "https://s-media-cache-ak0.pinimg.com/564x/3b/7d/6f/3b7d6f60e2d450b899c322266fc6edfd.jpg"),
                    ImageMetadata("", "https://cdn4.iconfinder.com/data/icons/STROKE/communications/png/400/avatar.png"),
                    ImageMetadata("", "http://www.uidownload.com/files/553/986/399/avatar-face-icon.png"),
                    ImageMetadata("", "http://www.iconninja.com/files/920/85/235/user-person-people-male-face-profile-mask-human-account-avatar-man-member-icon.png"))
            volunteers.add(Volunteer(
                    name = "Damian",
                    surname = "Szczepański",
                    description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
                    shortDescription = "This tutorial describes how to use Kotlin",
                    volunteerType = VolunteerType.Companion.Senior,
                    avatarImageUri = "https://s-media-cache-ak0.pinimg.com/564x/3b/7d/6f/3b7d6f60e2d450b899c322266fc6edfd.jpg",
                    projects = listOf(Project("Sadzenie drzewek", DESCRIPTION, emptyList(), projectImages), Project("Wykopanie rowu", DESCRIPTION), Project("Zbudowanie aplikacji")),
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
                    volunteerType = VolunteerType.Companion.Moderator,
                    avatarImageUri = "https://cdn4.iconfinder.com/data/icons/STROKE/communications/png/400/avatar.png",
                    addresses = listOf(
                            Address(
                                    "Iława",
                                    "14-200",
                                    "1 Maja",
                                    "35A",
                                    "7"))))
            volunteers.add(Volunteer(
                    name = "Mirosław",
                    surname = "Klosse",
                    description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
                    shortDescription = "This tutorial describes how to use Kotlin",
                    volunteerType = VolunteerType.Companion.Junior,
                    avatarImageUri = "http://www.uidownload.com/files/553/986/399/avatar-face-icon.png",
                    addresses = listOf(
                            Address(
                                    "Berlin",
                                    "945321",
                                    "Suzigkeiten Strasse",
                                    "35",
                                    "732"))))
            volunteers.add(Volunteer(
                    name = "Vladimir",
                    surname = "Boroviz",
                    description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
                    shortDescription = "This tutorial describes how to use Kotlin",
                    volunteerType = VolunteerType.Companion.Regular,
                    avatarImageUri = "http://www.iconninja.com/files/920/85/235/user-person-people-male-face-profile-mask-human-account-avatar-man-member-icon.png",
                    addresses = listOf(
                            Address(
                                    "Moskwa",
                                    "9556821",
                                    "Stalinowska ulica",
                                    "9897",
                                    "73"))))
            volunteers.addAll(volunteers)
            volunteers.addAll(volunteers)
            emitter.onNext(volunteers)
            emitter.onComplete()
        }
    }
}