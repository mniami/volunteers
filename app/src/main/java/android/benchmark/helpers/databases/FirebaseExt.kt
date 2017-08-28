package android.benchmark.helpers.databases

import android.benchmark.helpers.dataservices.databases.DbErrorException
import com.google.firebase.database.DatabaseError

fun DatabaseError.createReadException(): Throwable {
    return DbErrorException(DbErrorException.READ_EXCEPTION, this)
}
