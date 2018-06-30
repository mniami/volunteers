package android.benchmark.log

interface Logger {
    companion object {
        var instance : Logger = DefaultLogger()
    }
    fun log(logLevel: LogLevel, category : String?, message: String)
}

