package by.gsu.siemeljanov.fragments.lab10

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab7.*

class LabFragment7(private val lab: ListItem) : Fragment() {

    private lateinit var bluetooth: BluetoothAdapter
    private lateinit var pairedDevices: Set<BluetoothDevice>
    private var list = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab7, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        bluetooth = BluetoothAdapter.getDefaultAdapter()
        listView.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, list)
        button1.setOnClickListener {
            if (bluetooth.isEnabled) {
                toast(getString(R.string.already_on))
            } else {
                val turnOn = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(turnOn, 0)
                toast(getString(R.string.turned_on))
            }
        }
        button2.setOnClickListener {
            bluetooth.disable()
            toast(getString(R.string.turned_off))
        }
        button3.setOnClickListener {
            val getVisible = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            startActivityForResult(getVisible, 0)
        }
        button4.setOnClickListener {
            pairedDevices = bluetooth.bondedDevices
            list = ArrayList()
            for (bt in pairedDevices) {
                list.add(bt.name)
            }
            toast(getString(R.string.showing_paired_devices))
            listView.adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, list)
        }
    }

    private fun toast(string: String) {
        Toast.makeText(
            context,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}
