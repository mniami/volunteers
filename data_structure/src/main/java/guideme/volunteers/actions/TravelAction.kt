package guideme.volunteers.actions

import guideme.volunteers.domain.Person
import guideme.volunteers.domain.TravelDestination
import guideme.volunteers.domain.TravelDestinationRepository

class TravelAction (val destination: TravelDestination, val person: Person, val travelDestinationRepo : TravelDestinationRepository) : Action {
    override fun run (){

    }
}