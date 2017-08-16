package android.benchmark.helpers.android

import android.os.Bundle
import java.io.Serializable

fun Bundle.fromSerializable(key: String, value : Serializable) : Bundle{
    this.putSerializable(key, value)
    return this
}
fun Bundle.withStringValue(key: String, value : String) : Bundle{
    this.putString(key, value)
    return this
}