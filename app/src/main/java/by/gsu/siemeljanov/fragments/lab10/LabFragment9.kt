package by.gsu.siemeljanov.fragments.lab10

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.databinding.FragmentLab9Binding
import by.gsu.siemeljanov.labs.lab9.CopyPasteModel
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab9.*

class LabFragment9(private val lab: ListItem) : Fragment() {

    private lateinit var myClipboard: ClipboardManager
    private lateinit var myClip: ClipData
    private lateinit var binding: FragmentLab9Binding
    private var copypaste = CopyPasteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lab9, container, false)
        binding.copypaste = copypaste
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        myClipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        materialButton1.setOnClickListener {
            myClip = ClipData.newPlainText("text", copypaste.copy.get())
            myClipboard.setPrimaryClip(myClip)
            toast(getString(R.string.copied_text))
        }
        materialButton2.setOnClickListener {
            copypaste.paste.set(myClipboard.primaryClip?.getItemAt(0)?.text.toString())
            toast(getString(R.string.pasted_text))
        }
    }

    private fun toast(string: String) {
        Toast.makeText(
            context, string,
            Toast.LENGTH_SHORT
        ).show()
    }
}
