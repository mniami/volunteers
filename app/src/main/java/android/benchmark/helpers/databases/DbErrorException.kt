package android.benchmark.helpers.databases

class DbErrorException(val code: Int, val detailError: Any) : Exception() {
    companion object {
        val READ_EXCEPTION = 0
    }
}