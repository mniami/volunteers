package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.User
import org.junit.Test

import org.junit.Assert.*

class MockUserDataSourceTest {

    @Test
    fun update() {
        MockUserDataSource().update(User())
    }
}