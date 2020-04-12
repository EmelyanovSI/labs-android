package by.gsu.siemeljanov.fragments.lab20

import android.graphics.Matrix
import android.os.Bundle
import android.view.*
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab13.*
import kotlin.math.max
import kotlin.math.min

class LabFragment13(private val lab: ListItem) : Fragment() {

    private var matrix = Matrix()
    private var scale = 1f
    private lateinit var SGD: ScaleGestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lab13, container, false)
        SGD = ScaleGestureDetector(context, ScaleListener())
        view.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                SGD.onTouchEvent(motionEvent)
            }
            true
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
    }

    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor
            scale = max(0.1f, min(scale, 5.0f))
            matrix.setScale(scale, scale)
            imageView.imageMatrix = matrix
            return true
        }
    }
}
