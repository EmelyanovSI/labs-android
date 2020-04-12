package by.gsu.siemeljanov.fragments.lab20

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.MapsActivity
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab14.*

class LabFragment14(private val lab: ListItem) : Fragment() {

    val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab14, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        materialButton.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, lab.title)
            }
            startActivity(intent)
        }
    }
}
