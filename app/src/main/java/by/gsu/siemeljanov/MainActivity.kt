package by.gsu.siemeljanov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager
        get() = supportFragmentManager
    private val fragmentTransaction: FragmentTransaction
        get() = fragmentManager.beginTransaction()
    private val homeFragment
        get() = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fragment_container != null) {
            if (savedInstanceState != null) {
                return
            }
            fragmentTransaction
                .add(R.id.fragment_container, homeFragment)
                .commit()
        }
    }
}
