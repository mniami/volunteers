package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.domain.User
import org.junit.Test

class MockUserDataSourceTest {

    @Test
    fun update() {
        MockUserDataSource().update(User())
    }
}