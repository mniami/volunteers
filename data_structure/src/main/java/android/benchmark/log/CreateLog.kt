package android.benchmark.log

fun <T: Any> T.createLog(forClass: T): Log {
    return Log(forClass.javaClass.simpleName)
}