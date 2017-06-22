package android.benchmark.ui.utils

import android.content.Context
import android.widget.Toast

class ConfirmationToastAction {
    var toast: Toast? = null

    fun callAction(context: Context, toastContent: String, action: () -> Unit) {
        if (toast != null) {
            val isShown = toast?.view?.isShown()
            isShown?.let {
                if (it) {
                    toast?.cancel()
                    action()
                    return
                }
            }
        }
        toast = Toast.makeText(context, toastContent, Toast.LENGTH_SHORT)
        toast?.show()
    }
}