package by.gsu.siemeljanov.fragments.lab10

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab3.*
import java.io.IOException

class LabFragment3(private val lab: ListItem) : Fragment() {

    private var mediaRecorder = MediaRecorder()
    private var mediaPlayer = MediaPlayer()
    private var audioSavePathInDevice: String = null.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button1.setOnClickListener {
            if (checkPermission()) {
                button1.isEnabled = false
                button2.isEnabled = true
                button3.isEnabled = true
                audioSavePathInDevice =
                    Environment.getExternalStorageDirectory().absolutePath + "/" +
                            (100000000..999999999).random().toString() +
                            "_AudioRecording.3gp"
                mediaRecorderReady()
                try {
                    mediaRecorder.prepare()
                    mediaRecorder.start()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Toast.makeText(context, R.string.recording_started, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.permission_is_not_granted, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        button2.setOnClickListener {
            button1.isEnabled = true
            button2.isEnabled = false
            mediaRecorder.stop()
            Toast.makeText(context, R.string.recording_completed, Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            if (button2.isEnabled) {
                button2.callOnClick()
            }
            button1.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = true
            mediaPlayer = MediaPlayer()
            try {
                mediaPlayer.setDataSource(audioSavePathInDevice)
                mediaPlayer.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mediaPlayer.isLooping = true
            mediaPlayer.start()
            Toast.makeText(context, R.string.recording_playing, Toast.LENGTH_SHORT).show()
        }
        button4.setOnClickListener {
            button1.isEnabled = true
            button3.isEnabled = true
            button4.isEnabled = false
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaRecorderReady()
        }
    }

    private fun mediaRecorderReady() {
        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        mediaRecorder.setOutputFile(audioSavePathInDevice)
    }

    private fun checkPermission(): Boolean {
        if (checkSelfPermission(context!!, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED
            || checkSelfPermission(context!!, RECORD_AUDIO) != PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE, RECORD_AUDIO), 1)
        } else {
            return true
        }
        return checkSelfPermission(context!!, WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED
                && checkSelfPermission(context!!, RECORD_AUDIO) == PERMISSION_GRANTED
    }
}
