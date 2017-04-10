package android.benchmark.domain

import java.io.Serializable

class Volunteer(val needies: List<Needy> = emptyList(),
                name: String = "",
                surname: String = "",
                avatarImageUri: String = "",
                shortDescription: String = "",
                description: String = "",
                volunteerType : String = "",
                projects : List<Project> = emptyList(),
                addresses: List<Address> = emptyList()) :
        Person(name, surname, avatarImageUri, shortDescription, description, volunteerType, projects, addresses)

class Needy(val volunteers: List<Volunteer> = emptyList(),
            name: String = "",
            surname: String = "",
            avatarImageUri: String = "",
            shortDescription: String = "",
            description: String = "",
            volunteerType : String = "",
            projects : List<Project> = emptyList(),
            addresses: List<Address> = emptyList()) :
        Person(name, surname, avatarImageUri, shortDescription, description, volunteerType, projects, addresses)

open class Person (
        val name: String = "",
        val surname: String = "",
        val avatarImageUri: String = "",
        val shortDescription: String = "",
        val description: String = "",
        val volunteerType : String = "",
        val projects : List<Project> = emptyList(),
        val addresses: List<Address> = emptyList()) : Serializable {

}
class VolunteerType {
    companion object {
        const val Admin = "Admin"
        const val Moderator = "Moderator"
        const val Senior = "Senior Volunteer"
        const val Regular = "Regular Volunteer"
        const val Junior = "Junior Volunteer"
    }
}

open class Project (val name : String = "",
                    val description : String = "",
                    val volunteersInvolved: List<Volunteer> = emptyList(),
                    val images : List<ImageMetadata> = emptyList()) : Serializable

open class ImageMetadata (val name : String = "",
                          val url : String = "") : Serializable

class Address(
        val city: String = "",
        val zip: String = "",
        val street: String = "",
        val house: String = "",
        val flat: String = "",
        val addressType: LiveAddress = LiveAddress()
) : Serializable

open class AddressType : Serializable
class LiveAddress : AddressType()