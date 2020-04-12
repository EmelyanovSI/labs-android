package by.gsu.siemeljanov.fragments.lab30

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.databinding.FragmentLab22Binding
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab22.*

class LabFragment22(private val lab: ListItem) : Fragment() {

    private val admin = "admin"
    private var counter = 3
    var counterString = ObservableField<String>()
    var login = ObservableField<String>()
    var password = ObservableField<String>()
    private lateinit var binding: FragmentLab22Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        counterString.set(counter.toString())
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lab22, container, false)
        binding.user = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        text4.visibility = View.GONE
        materialButton1.setOnClickListener {
            if (login.get() == admin && password.get() == admin) {
                Toast.makeText(context, R.string.text_redirecting, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.wrong_credentials, Toast.LENGTH_SHORT).show()
                text4.visibility = View.VISIBLE
                --counter
                counterString.set(counter.toString())
                if (counter == 0) {
                    materialButton1.isEnabled = false
                }
            }
        }
        materialButton2.setOnClickListener {
            login.set("")
            password.set("")
        }
    }

}
