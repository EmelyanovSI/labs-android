package by.gsu.siemeljanov.labs.lab1

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import by.gsu.siemeljanov.R
import java.util.*

class Dialog : DialogFragment() {

    private fun toast(btn: Int) {
        Toast.makeText(
            context,
            getString(R.string.you_clicked) + " " +
                    getString(btn).toLowerCase(Locale.getDefault()) + " " +
                    getString(R.string.button).toLowerCase(Locale.getDefault()),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(R.string.text_decision)
            .setPositiveButton(R.string.yes) { _, _ ->
                toast(R.string.yes)
            }
            .setNegativeButton(R.string.no) { _, _ ->
                toast(R.string.no)
            }
        return builder.create()
    }
}
