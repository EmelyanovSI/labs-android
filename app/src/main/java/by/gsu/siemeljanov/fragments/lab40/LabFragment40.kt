package by.gsu.siemeljanov.fragments.lab40

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab40.*

class LabFragment40(private val lab: ListItem) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab40, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
    }

}
