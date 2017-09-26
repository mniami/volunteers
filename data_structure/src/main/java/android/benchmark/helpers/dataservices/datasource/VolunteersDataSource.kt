package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.*
import android.benchmark.helpers.content.ResourceManager
import android.benchmark.helpers.content.Resources
import android.benchmark.helpers.dataservices.databases.Database
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class VolunteersDataSource(resourcesManager: ResourceManager, private val database: Database) : ObservableDataSource<Volunteer> {
    companion object {
        val ID = KeyDataSourceId("volunteers")
    }

//    val volunteers = arrayListOf<Volunteer>()
//    val DESCRIPTION = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View"

    init {
//        val projectImages = mutableListOf(
//                ImageMetadata("", "https://s-media-cache-ak0.pinimg.com/564x/3b/7d/6f/3b7d6f60e2d450b899c322266fc6edfd.jpg"),
//                ImageMetadata("", "https://cdn4.iconfinder.com/data/icons/STROKE/communications/png/400/avatar.png"),
//                ImageMetadata("", "http://www.uidownload.com/files/553/986/399/avatar-face-icon.png"),
//                ImageMetadata("", "http://www.iconninja.com/files/920/85/235/user-person-people-male-face-profile-mask-human-account-avatar-man-member-icon.png"))
//        volunteers.add(Volunteer(
//                name = "Damian",
//                surname = "Szczepański",
//                description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
//                shortDescription = "This tutorial describes how to use Kotlin",
//                volunteerType = VolunteerType.Companion.Senior,
//                avatarImageUri = "https://s-media-cache-ak0.pinimg.com/564x/3b/7d/6f/3b7d6f60e2d450b899c322266fc6edfd.jpg",
//                projects = hashMapOf(
//                        Pair("sadzenie_drzewek", Project("Sadzenie drzewek", DESCRIPTION, resourcesManager.getString(Resources.ProjectLongDescription), mutableListOf(), projectImages)),
//                        Pair("wykopanie rowu", Project("Wykopanie rowu", DESCRIPTION)),
//                        Pair("zbudowanie aplikacji", Project("Zbudowanie aplikacji"))),
//                addresses = hashMapOf(Pair("home",
//                        Address(
//                                "Bydgoszcz",
//                                "85-135",
//                                "Bielicka",
//                                "14",
//                                "13")))))
//        volunteers.last().projects["sadzenie_drzewek"]!!.volunteersInvolved.add(volunteers.last())
//        volunteers.add(Volunteer(
//                name = "Kamila",
//                surname = "Grochowiecka",
//                description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
//                shortDescription = "This tutorial describes how to use Kotlin",
//                volunteerType = VolunteerType.Companion.Moderator,
//                avatarImageUri = "https://cdn4.iconfinder.com/data/icons/STROKE/communications/png/400/avatar.png",
//                addresses = hashMapOf(
//                        Pair("home",
//                                Address(
//                                        "Iława",
//                                        "14-200",
//                                        "1 Maja",
//                                        "35A",
//                                        "7")))))
//        volunteers.add(Volunteer(
//                name = "Mirosław",
//                surname = "Klosse",
//                description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
//                shortDescription = "This tutorial describes how to use Kotlin",
//                volunteerType = VolunteerType.Companion.Junior,
//                avatarImageUri = "http://www.uidownload.com/files/553/986/399/avatar-face-icon.png",
//                addresses = hashMapOf(
//                        Pair("home",
//                                Address(
//                                        "Berlin",
//                                        "945321",
//                                        "Suzigkeiten Strasse",
//                                        "35",
//                                        "732")))))
//        volunteers.add(Volunteer(
//                name = "Vladimir",
//                surname = "Boroviz",
//                description = "This tutorial describes how to use Kotlin Android Extensions to improve support ... dependent on runtime, they require annotating fields for each View",
//                shortDescription = "This tutorial describes how to use Kotlin",
//                volunteerType = VolunteerType.Companion.Regular,
//                avatarImageUri = "http://www.iconninja.com/files/920/85/235/user-person-people-male-face-profile-mask-human-account-avatar-man-member-icon.png",
//                addresses = hashMapOf(
//                        Pair("home",
//                                Address(
//                                        "Moskwa",
//                                        "9556821",
//                                        "Stalinowska ulica",
//                                        "9897",
//                                        "73")))))
//        volunteers.addAll(volunteers)
//        volunteers.addAll(volunteers)
    }

    override val id: DataSourceId
        get() = ID

    override val data: ObservableData<Volunteer>
        get() {
            return ObservableDataImpl(database.getVolunteers())
        }
}