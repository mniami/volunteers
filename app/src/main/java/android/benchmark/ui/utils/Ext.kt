package android.benchmark.ui.utils


fun <T, K> List<T>.withPosition(position : Int, default : K, convert : (T) -> K) : K {
    val element = this[position]
    if (element != null){
        return convert(element)
    }
    return default
}