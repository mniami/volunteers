package guideme.volunteers.ui.dialogs

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.EditText
import guideme.volunteers.R

class UrlRequestDialog {
    fun show(title: String, message: String, context: Context?, onFinished: (url: String?) -> Unit) {
        if (context == null) {
            onFinished(null)
            return
        }
        val editText = EditText(context)
        val alert = AlertDialog.Builder(context)

        alert.setTitle(title)
        alert.setMessage(message)

        alert.setView(editText)
        alert.setPositiveButton(context.getString(R.string.accept_dialog_button_text)) { dialog, whichButton ->
            //What ever you want to do with the value
            val url = editText.text.toString()
            onFinished(url)
        }
        alert.setNegativeButton(context.getString(R.string.cancel_dialog_button_text)) { dialog, whichButton ->
            // what ever you want to do with No option.
            onFinished(null)
        }
        alert.show()
    }
}
