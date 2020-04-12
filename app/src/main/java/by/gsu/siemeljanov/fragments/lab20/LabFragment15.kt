package by.gsu.siemeljanov.fragments.lab20

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab15.*

class LabFragment15(private val lab: ListItem) : Fragment() {

    private lateinit var bmp: Bitmap
    private lateinit var operation: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab15, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        bmp = imageView.drawable.toBitmap()
        materialButton1.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            val red = 0.33
            val green = 0.59
            val blue = 0.11
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(
                        i, j, Color.argb(
                            Color.alpha(p),
                            Color.red(p) * red.toInt(),
                            Color.green(p) * green.toInt(),
                            Color.blue(p) * blue.toInt()
                        )
                    )
                }
            }
            imageView.setImageBitmap(operation)
        }
        materialButton2.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(
                        i, j, Color.argb(
                            Color.alpha(p),
                            Color.red(p) + 100,
                            Color.green(p) + 100,
                            Color.blue(p) + 100
                        )
                    )
                }
            }
            imageView.setImageBitmap(operation)
        }
        materialButton3.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(
                        i, j, Color.argb(
                            Color.alpha(p),
                            Color.red(p) - 50,
                            Color.green(p) - 50,
                            Color.blue(p) - 50
                        )
                    )
                }
            }
            imageView.setImageBitmap(operation)
        }
        materialButton4.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(i, j, Color.argb(Color.alpha(p), Color.red(p) + 150, 0, 0))
                }
            }
            imageView.setImageBitmap(operation)
        }
        materialButton5.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(i, j, Color.argb(Color.alpha(p), 0, Color.green(p) + 150, 0))
                }
            }
            imageView.setImageBitmap(operation)
        }
        materialButton6.setOnClickListener {
            operation = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
            for (i in 0 until bmp.width) {
                for (j in 0 until bmp.height) {
                    val p = bmp.getPixel(i, j)
                    operation.setPixel(i, j, Color.argb(Color.alpha(p), 0, 0, Color.blue(p) + 150))
                }
            }
            imageView.setImageBitmap(operation)
        }
    }
}
