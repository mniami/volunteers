package android.benchmark.ui.fragments.benchmark

import android.benchmark.R
import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.utils.BitmapUtils
import android.benchmark.ui.views.ImageBreakerView
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

internal class BenchmarkFragment : Fragment(), IBenchmarkFragment {
    val presenter by lazy { BenchmarkPresenter(this, activity as IMainActivity, bitmapUtils = BitmapUtils()) }
    val imageBreakerView by lazy { view?.findViewById(R.id.imageBreakerView) as? ImageBreakerView }

    override fun getFragment(): Fragment {
        return this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.animation_benchmark_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate(context)
    }

    override fun startAnimation() {
        imageBreakerView?.startAnimation()
    }

    override fun onBreakerViewBitmapLoaded(bitmap: Bitmap) {
        imageBreakerView?.bitmap = bitmap
    }

    override fun onAnimationError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG)
    }
}