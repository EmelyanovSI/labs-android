package by.gsu.siemeljanov.labs

import android.Manifest.permission.ACCESS_NOTIFICATION_POLICY
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.*
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab4.*

class LabFragment4(private val lab: ListItem) : Fragment() {

    private var myAudioManager: AudioManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab4, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        text2.text = getString(R.string.laboratory_work)
        button2.isEnabled = false
        if (checkPermission()) {
            myAudioManager = activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        } else {
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            Toast.makeText(context, R.string.permission_is_not_granted, Toast.LENGTH_SHORT).show()
        }

        button1.setOnClickListener {
            myAudioManager?.ringerMode = AudioManager.RINGER_MODE_NORMAL
            toast(R.string.ring)
        }
        button2.setOnClickListener {
            myAudioManager?.ringerMode = AudioManager.RINGER_MODE_SILENT
            toast(R.string.silent)
        }
        button3.setOnClickListener {
            myAudioManager?.ringerMode = AudioManager.RINGER_MODE_VIBRATE
            toast(R.string.vibrate)
        }
        button4.setOnClickListener {
            when (myAudioManager?.ringerMode) {
                AudioManager.RINGER_MODE_NORMAL -> toast(R.string.ring)
                AudioManager.RINGER_MODE_SILENT -> toast(R.string.silent)
                AudioManager.RINGER_MODE_VIBRATE -> toast(R.string.vibrate)
            }
        }
    }

    private fun checkPermission(): Boolean {
        if (checkSelfPermission(
                context!!,
                ACCESS_NOTIFICATION_POLICY
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(ACCESS_NOTIFICATION_POLICY), 1)
        } else {
            return true
        }
        return checkSelfPermission(
            context!!,
            ACCESS_NOTIFICATION_POLICY
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun toast(mode: Int) {
        Toast.makeText(
            context,
            "${getString(R.string.now_in)} ${getString(mode)} ${getString(R.string.mode)}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
