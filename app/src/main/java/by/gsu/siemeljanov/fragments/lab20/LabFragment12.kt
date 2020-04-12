package by.gsu.siemeljanov.fragments.lab20

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab12.*
import java.io.FileNotFoundException

class LabFragment12(private val lab: ListItem) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab12, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        materialButton.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            val screenshotUri = Uri.parse("android.resource://by.gsu.siemeljanov/drawable/code")
            try {
                val stream = context?.contentResolver?.openInputStream(screenshotUri)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            sharingIntent.type = "image/jpeg"
            sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
            startActivity(
                Intent.createChooser(
                    sharingIntent,
                    getString(R.string.share_image_using)
                )
            )
        }
    }

}
