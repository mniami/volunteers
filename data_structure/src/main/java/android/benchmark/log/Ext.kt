package android.benchmark.log

import kotlin.reflect.KClass

fun <T: Any> T.createLog(forClass: KClass<T>): Log {
    return Log(forClass.simpleName)
}