package android.benchmark.actions

import android.benchmark.domain.Person
import android.benchmark.domain.TravelDestination
import android.benchmark.domain.TravelDestinationRepository

class TravelAction (val destination: TravelDestination, val person: Person, val travelDestinationRepo : TravelDestinationRepository) : Action {
    override fun run (){

    }
}