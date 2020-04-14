package by.gsu.siemeljanov.fragments.lab30

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab23.*
import java.util.concurrent.TimeUnit

class LabFragment23(private val lab: ListItem) : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var myHandler: Handler
    private var startTime = 0
    private var finalTime = 0
    private var oneTimeOnly = 0
    private var forwardTime = 0
    private var backwardTime = 0
    private val time = "%d min, %d sec"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab23, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        startTime = 0
        finalTime = 0
        oneTimeOnly = 0
        forwardTime = 5000
        backwardTime = 5000
        myHandler = Handler()
        mediaPlayer = MediaPlayer.create(context, R.raw.song)
        materialButton2.isEnabled = false
        materialButton1.setOnClickListener {
            if ((startTime - backwardTime) > 0) {
                startTime -= backwardTime
                mediaPlayer.seekTo(startTime)
            }
        }
        materialButton2.setOnClickListener {
            mediaPlayer.pause()
            materialButton2.isEnabled = false
            materialButton3.isEnabled = true
        }
        materialButton3.setOnClickListener {
            mediaPlayer.start()
            finalTime = mediaPlayer.duration
            startTime = mediaPlayer.currentPosition
            if (oneTimeOnly == 0) {
                seekBar.max = finalTime
                oneTimeOnly = 1
            }
            text3.text = String.format(
                time,
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                )
            )
            text4.text = String.format(
                time,
                TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong())
                )
            )
            seekBar.progress = startTime
            myHandler.postDelayed(updateSongTime, 100)
            materialButton2.isEnabled = true
            materialButton3.isEnabled = false
        }
        materialButton4.setOnClickListener {
            if ((startTime + forwardTime) <= finalTime) {
                startTime += forwardTime
                mediaPlayer.seekTo(startTime)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
        myHandler.removeCallbacks(updateSongTime)
    }

    private val updateSongTime: Runnable = object : Runnable {
        override fun run() {
            startTime = mediaPlayer.currentPosition
            text5.text = String.format(
                time,
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                )
            )
            seekBar.progress = startTime
            myHandler.postDelayed(this, 100)
        }
    }

}
