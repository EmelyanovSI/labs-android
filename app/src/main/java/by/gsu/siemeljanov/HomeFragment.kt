package by.gsu.siemeljanov

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.gsu.siemeljanov.adapter.ListAdapter
import by.gsu.siemeljanov.adapter.OnListItemClickListener
import by.gsu.siemeljanov.labs.*
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), OnListItemClickListener {
    private lateinit var labs: ArrayList<ListItem>
    private lateinit var fragments: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        labsInit()
        fragmentsInit()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                layoutManager.orientation
            )
        )
        recyclerView.adapter = ListAdapter(labs, R.layout.list_item, this)
    }

    override fun onItemClick(item: ListItem, position: Int) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragments[item.number.toInt()])
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun labsInit() {
        labs = ArrayList()
        val titles = resources.getStringArray(R.array.lab_title)
        titles.forEach { title ->
            labs.add(
                ListItem(
                    context,
                    title,
                    getString(R.string.lab).toLowerCase(Locale.getDefault()),
                    titles.indexOf(title)
                )
            )
        }
    }

    private fun fragmentsInit() {
        fragments = ArrayList()
        fragments.add(LabFragment1(labs[0]))
        fragments.add(LabFragment2(labs[1]))
        fragments.add(LabFragment3(labs[2]))
        fragments.add(LabFragment4(labs[3]))
        fragments.add(LabFragment5(labs[4]))
        fragments.add(LabFragment6(labs[5]))
        fragments.add(LabFragment7(labs[6]))
        fragments.add(LabFragment8(labs[7]))
        fragments.add(LabFragment9(labs[8]))
        fragments.add(LabFragment10(labs[9]))
    }
}
