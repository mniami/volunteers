package android.benchmark.ui.fragments.animationBenchmark

import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.fragments.base.IFragmentContainer
import android.benchmark.ui.utils.BitmapUtils
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment

internal class BenchmarkPresenter(val animationBenchmarkFragment:
                                  IBenchmarkFragment, val mainActivity: IMainActivity, val bitmapUtils: BitmapUtils) : IFragmentContainer {
    companion object {
        fun createInstance(bundle: Bundle = Bundle(), mainActivity: IMainActivity, bitmapUtils: BitmapUtils = BitmapUtils()): BenchmarkPresenter {
            return BenchmarkPresenter(AnimationBenchmarkFragment(), mainActivity, bitmapUtils)
        }
    }

    val handler: Handler = Handler(Looper.getMainLooper())

    fun onCreate(context: Context) {
        loadBitmap(context)
    }

    private fun loadBitmap(context: Context) {
        bitmapUtils.loadRandomBitmap(context, { bitmap ->
            animationBenchmarkFragment.onBreakerViewBitmapLoaded(bitmap)
            handler.post { animationBenchmarkFragment.startAnimation() }
        }, { errorMessage ->
            animationBenchmarkFragment.onAnimationError(errorMessage)
        })
    }

    override fun getFragment(): Fragment {
        return animationBenchmarkFragment.getFragment()
    }
}

