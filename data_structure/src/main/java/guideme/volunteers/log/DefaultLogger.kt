package guideme.volunteers.log

class DefaultLogger : Logger {
    override fun log(logLevel: LogLevel, category: String?, message: String) {
        println("$category: $message")
    }
}