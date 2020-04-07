package by.gsu.siemeljanov.model

import android.content.Context
import by.gsu.siemeljanov.R
import java.util.*

class ListItem(
    var context: Context?,
    var title: String = context?.getString(R.string.android).toString(),
    var name: String = context?.getString(R.string.item).toString()
        .toLowerCase(Locale.getDefault()),
    var number: Number = 0
)
