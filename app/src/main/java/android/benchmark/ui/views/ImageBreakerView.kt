package android.benchmark.ui.views

import android.benchmark.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView


class ImageBreakerView : ViewGroup {
    protected var imageViews: Array<ImageView> = arrayOf<ImageView>()
    protected var initialized: Boolean = false
    protected var paint: Paint = Paint()

    var bitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)

    constructor (context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit(attrs)
    }

    constructor (context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        onInit(attrs)
    }

    constructor (context: Context?) : super(context) {
        onInit(null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val srcR = Rect(0, 0, bitmap.width, bitmap.height)

        canvas?.drawBitmap(bitmap, srcR, clipBounds, paint)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //createViews()
        invalidate()
    }

    private fun createViews() {
        if (bitmap.width > 1 && bitmap.height > 1) {
            //imageViews = Array(6, { i -> getImageOnPosition(bitmap.width, bitmap.height, i) })
            initialized = true
        }
        invalidate()
    }

    fun startAnimation() {
        if (!initialized) {
            createViews()
        }
        if (imageViews.count() > 0) {
            val firstView = imageViews[0]
            firstView.animate().setDuration(500).rotation(4f).start()
        }
    }

    private fun getImageOnPosition(bWidth: Int, bHeight: Int, index: Int): ImageView {
        val partWidth = Math.max(Math.round(bWidth / 3f), 1)
        val partHeight = Math.max(Math.round(bHeight / 3f), 1)
        val tmpArray = IntArray(partWidth * partHeight)

        val posX = index % 3 * partWidth
        val posY = index / 3 * partHeight

        bitmap.getPixels(tmpArray, 0, partWidth * (index + 1), posX, posY, partWidth, partHeight)

        val tmpBitmap = Bitmap.createBitmap(tmpArray, partWidth, partHeight, Bitmap.Config.ARGB_8888)
        val imageView = ImageView(context)

        imageView.setImageBitmap(tmpBitmap)

        return imageView
    }

    private fun onInit(attrs: AttributeSet?) {
        if (attrs != null) {
            initAttr(attrs)
        }
    }

    private fun initAttr(attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ImageBreakerView, 0, 0)
        if (typedArray != null) {
        }
    }
}