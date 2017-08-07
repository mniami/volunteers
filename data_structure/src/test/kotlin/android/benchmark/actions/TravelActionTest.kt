package android.benchmark.actions

import android.benchmark.domain.Level
import android.benchmark.domain.Person
import android.benchmark.domain.TravelDestination
import android.benchmark.domain.TravelDestinationRepository
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