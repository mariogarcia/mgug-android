package mgug.app.widget

import android.app.AlertDialog
import android.content.Context

class Widgets {

    static void showAlertDialog(Context context, @DelegatesTo(AlertDialog.Builder) Closure<?> closure) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)

        builder.with(closure)
        builder.create().show()
    }
}