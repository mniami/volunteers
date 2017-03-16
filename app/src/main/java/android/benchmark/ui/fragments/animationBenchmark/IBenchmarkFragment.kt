package android.benchmark.ui.fragments.animationBenchmark

import android.benchmark.ui.fragments.base.IFragmentContainer
import android.graphics.Bitmap
import android.support.v4.app.Fragment

internal interface IBenchmarkFragment : IFragmentContainer {
    fun startAnimation()
    override fun getFragment(): Fragment
    fun onBreakerViewBitmapLoaded(bitmap: Bitmap)
    fun onAnimationError(errorMessage: String)
}