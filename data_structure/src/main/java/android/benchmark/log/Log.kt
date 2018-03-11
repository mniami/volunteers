package android.benchmark.log

class Log(private val category: String?,
          var logLevel: LogLevel = LogLevel.DEBUG) {

    fun i(getLog: () -> String) {
        if (logLevel >= LogLevel.INFO) {
            log(getLog())
        }
    }

    fun w(getLog: () -> String) {
        if (logLevel >= LogLevel.WARNING) {
            log(getLog())
        }
    }

    fun d(getLog: () -> String) {
        if (logLevel >= LogLevel.DEBUG) {
            log(getLog())
        }
    }

    open fun log(message: String) {
        Logger.instance.log(logLevel, category, message)
    }
}