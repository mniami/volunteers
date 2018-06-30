package guideme.volunteers.actions

import guideme.volunteers.domain.Level
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.TravelDestination
import guideme.volunteers.domain.TravelDestinationRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class TravelActionTest {

    private lateinit var travelAction : TravelAction

    var travelDestination : TravelDestination = mock()
    var travelDestinationRepository : TravelDestinationRepository = mock()
    var person : Person = mock()

    @Before
    fun setUp(){
        travelAction = TravelAction(travelDestination, person, travelDestinationRepository)
    }

    @Test
    fun runActionTest(){
        whenever(travelDestination.name).thenReturn("Barcelona")
        whenever(travelDestination.difficultyLevel).thenReturn(Level())

        travelAction.run()


    }
}