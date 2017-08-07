package android.benchmark.helpers.databases

import com.google.firebase.database.DatabaseError

fun DatabaseError.createReadException(): Throwable? {
    return DbErrorException(DbErrorException.READ_EXCEPTION, this)
}
