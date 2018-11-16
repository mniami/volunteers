package guideme.volunteers.domain

import guideme.volunteers.annotations.Required
import java.io.Serializable

data class Volunteer(override val id: String = "",
                     val needyList: List<Needy> = emptyList(),
                     val volunteerType: String = "",
                     override val person: Person = Person()) : Human {
    fun cloneWithId(id : String) : Volunteer{
        return Volunteer(id = id,
                needyList = needyList,
                volunteerType = volunteerType,
                person = person)
    }
}

data class Needy(override val id: String = "",
                 val volunteers: List<Volunteer> = emptyList(),
                 override val person: Person = Person()) : Human

interface Human : Serializable {
    val id : String
    val person : Person
}

data class Person(
        val privilege: Privilege = Privilege.USER,
        @Required
        val name: String = "",
        val surname: String = "",
        val avatarImageUri: String = "",
        val personalityDescription: String = "",
        val description: String = "",
        val shortDescription: String = "",
        @Required
        val email: String = "",
        val skills: Skills = Skills(),
        val activity: Activity = Activity(),
        val projects: Map<String, Project> = hashMapOf(),
        val addresses: Map<String, Address> = hashMapOf(),
        var key: String = "",
        var scope: Scope = Scope.GLOBAL,
        val groups: MutableList<Group> = mutableListOf()) : Serializable

data class User(
        override val id: String = "",
        val volunteers: MutableList<Volunteer> = mutableListOf(),
        override val person: Person = Person()) : Human

enum class Scope {
    PRIVATE,
    GROUP,
    GLOBAL
}

data class ActionDate(val value: Long = 0) : Serializable
data class Action(val name: String = "", val date: ActionDate = ActionDate()) : Serializable
data class Activity(val actions: MutableList<Action> = mutableListOf()) : Serializable
data class Skills(val stamina: Level = Level(),
                  val engagement: Level = Level(),
                  val humor: Level = Level(),
                  val enjoyment: Level = Level(),
                  val psychicStrength: Level = Level(),
                  val social: Level = Level(),
                  val intuition: Level = Level(),
                  val marketing: Level = Level(),
                  val talkative: Level = Level(),
                  val technical: Level = Level(),
                  val economic: Level = Level(),
                  val laziness: Level = Level(),
                  val leading: Level = Level(),
                  val introvert: Level = Level(),
                  val traveling: Level = Level()) : Serializable

data class Group(val name: String = "", val humans: MutableList<Human> = mutableListOf()) : Serializable

data class Level(val level: Float = 0f) : Serializable {
    operator fun times(skill2: Level): Level = Level(level * skill2.level)

    operator fun times(skillLevel: Float): Level = Level(level * skillLevel)

    operator fun div(skill2: Level): Level = Level(level / skill2.level)

    operator fun div(skillLevel: Float): Level = Level(level / skillLevel)

    operator fun plus(skillLevel: Float): Level = Level(level + skillLevel)

    operator fun plus(skill2: Level): Level = Level(level + skill2.level)
}

operator fun Float.div(skill: Level): Level = Level(this / skill.level)

operator fun Float.times(skill: Level): Level = Level(this * skill.level)

data class Project(val name: String = "",
                   val description: String = "",
                   val longDescription: String = "",
                   val volunteersInvolved: MutableList<Volunteer> = mutableListOf(),
                   val images: MutableList<ImageMetadata> = mutableListOf(),
                   val needyList: MutableList<Needy> = mutableListOf()) : Serializable

data class ImageMetadata(val name: String = "",
                         val url: String = "") : Serializable

data class Address(
        val city: String = "",
        val zip: String = "",
        val street: String = "",
        val house: String = "",
        val flat: String = ""
) : Serializable

data class TravelDestination(val name: String, val difficultyLevel: Level) : Serializable

enum class Privilege {
    ADMIN,
    GROUP_ADMIN,
    USER
}