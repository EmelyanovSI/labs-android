package by.gsu.siemeljanov.labs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab2.*

class LabFragment2(private val lab: ListItem) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title

        val zoom = AnimationUtils.loadAnimation(context, R.anim.zoom)
        val clockwise = AnimationUtils.loadAnimation(context, R.anim.clockwise)
        val fade = AnimationUtils.loadAnimation(context, R.anim.fade)
        val blink = AnimationUtils.loadAnimation(context, R.anim.blink)
        val move = AnimationUtils.loadAnimation(context, R.anim.move)
        val slide = AnimationUtils.loadAnimation(context, R.anim.slide)

        button1.setOnClickListener { imageView.startAnimation(zoom) }
        button2.setOnClickListener { imageView.startAnimation(clockwise) }
        button3.setOnClickListener { imageView.startAnimation(fade) }
        button4.setOnClickListener { imageView.startAnimation(blink) }
        button5.setOnClickListener { imageView.startAnimation(move) }
        button6.setOnClickListener { imageView.startAnimation(slide) }
    }
}
