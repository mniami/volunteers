package android.benchmark.ui.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class CustomView : View {
    protected var bitmap: Bitmap = Bitmap.createBitmap(0, 0, Bitmap.Config.ALPHA_8)
    protected var bitmapSource: String = ""

    constructor(context: Context) : super(context) {
        onInit(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit(attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    private fun onInit(attrs: AttributeSet?) {
        if (attrs != null) {

        }
    }
}
