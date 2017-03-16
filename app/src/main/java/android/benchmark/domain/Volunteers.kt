package android.benchmark.domain

class Volunteer(val needies: List<Needy> = emptyList(), name: String = "",
                surname: String = "",
                avatarImageUri: String = "",
                addresses: List<Address> = emptyList()) :
        Person(name, surname, avatarImageUri, addresses)

class Needy(val volunteers: List<Volunteer> = emptyList(),
            name: String = "",
            surname: String = "",
            avatarImageUri: String = "",
            addresses: List<Address> = emptyList()) :
        Person(name, surname, avatarImageUri, addresses)

open class Person(
        val name: String = "",
        val surname: String = "",
        val avatarImageUri: String = "",
        val addresses: List<Address> = emptyList())

class Address(
        val city: String = "",
        val zip: String = "",
        val street: String = "",
        val house: String = "",
        val flat: String = "",
        val addressType: LiveAddress = LiveAddress()
)

open class AddressType
class LiveAddress : AddressType()