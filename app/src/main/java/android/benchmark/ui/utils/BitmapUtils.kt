package android.benchmark.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.net.URL
import java.util.regex.Pattern

class BitmapUtils {
    fun loadRandomBitmap(context: Context, onLoaded: (Bitmap) -> Unit, onError: (String) -> Unit) {
        val url = URL("https://pexels.com")
        val request = StringRequest(Request.Method.GET, url.toString(), Response.Listener<String>({
            response ->
            run {
                var regex = Pattern.compile("<article class=\"photo-item\">\n<a.*\n<img.*src=\"(.*)\"/>")
                val matcher = regex.matcher(response)
                if (matcher.find()) {
                    val url = matcher.group(1)
                    if (url != null) {
                        loadRandomBitmap(context, url, onLoaded)
                    }
                }
            }
        }), Response.ErrorListener {
            error ->
            run {
                onError.invoke(error.toString())
            }
        })
        Volley.newRequestQueue(context).add(request)
    }

    fun loadRandomBitmap(context: Context, bitmapUrl: String, onLoaded: (Bitmap) -> Unit) {
        val request = ImageRequest(bitmapUrl, Response.Listener<Bitmap> { bitmap ->
            onLoaded.invoke(bitmap)
        }, 1024, 1024, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888, Response.ErrorListener { })
        val queue = Volley.newRequestQueue(context)
        queue.add(request)
        queue.start()
    }

}