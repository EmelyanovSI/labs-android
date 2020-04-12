package by.gsu.siemeljanov.fragments.lab20

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.databinding.FragmentLab17Binding
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab17.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class LabFragment17(private val lab: ListItem) : Fragment() {

    var data = ""
    private val file: String = lab.title
    private lateinit var binding: FragmentLab17Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lab17, container, false)
        binding.data = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        materialButton1.setOnClickListener {
            try {
                val fOut: FileOutputStream = activity!!.openFileOutput(file, Context.MODE_PRIVATE)
                fOut.write(data.toByteArray())
                fOut.close()
                Toast.makeText(context, R.string.file_saved, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        materialButton2.setOnClickListener {
            try {
                val fileInputStream: FileInputStream = activity!!.openFileInput(file)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                text3.text = stringBuilder.toString()
                Toast.makeText(context, R.string.file_loaded, Toast.LENGTH_SHORT).show()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

}
