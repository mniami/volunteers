package guideme.volunteers.log

fun <T: Any> T.createLog(forClass: T): Log {
    return Log(forClass.javaClass.simpleName)
}