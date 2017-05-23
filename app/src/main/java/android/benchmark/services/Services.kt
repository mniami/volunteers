package android.benchmark.services

import android.benchmark.services.dataservices.DataServiceMock
import android.benchmark.services.dataservices.IDataService

class Services {
    companion object {
        var dataService: IDataService = DataServiceMock()
    }
}