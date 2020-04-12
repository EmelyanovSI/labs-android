package by.gsu.siemeljanov

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.gsu.siemeljanov.adapter.ListAdapter
import by.gsu.siemeljanov.adapter.OnListItemClickListener
import by.gsu.siemeljanov.fragments.lab10.*
import by.gsu.siemeljanov.fragments.lab20.*
import by.gsu.siemeljanov.fragments.lab30.*
import by.gsu.siemeljanov.fragments.lab40.*
import by.gsu.siemeljanov.fragments.lab50.*
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
        fragments.apply {
            add(LabFragment1(labs[0]))
            add(LabFragment2(labs[1]))
            add(LabFragment3(labs[2]))
            add(LabFragment4(labs[3]))
            add(LabFragment5(labs[4]))
            add(LabFragment6(labs[5]))
            add(LabFragment7(labs[6]))
            add(LabFragment8(labs[7]))
            add(LabFragment9(labs[8]))
            add(LabFragment10(labs[9]))

            add(LabFragment11(labs[10]))
            add(LabFragment12(labs[11]))
            add(LabFragment13(labs[12]))
            add(LabFragment14(labs[13]))
            add(LabFragment15(labs[14]))
            add(LabFragment16(labs[15]))
            add(LabFragment17(labs[16]))
            add(LabFragment18(labs[17]))
            add(LabFragment19(labs[18]))
            add(LabFragment20(labs[19]))

            add(LabFragment21(labs[20]))
            add(LabFragment22(labs[21]))
            add(LabFragment23(labs[22]))
            add(LabFragment24(labs[23]))
            add(LabFragment25(labs[24]))
            add(LabFragment26(labs[25]))
            add(LabFragment27(labs[26]))
            add(LabFragment28(labs[27]))
            add(LabFragment29(labs[28]))
            add(LabFragment30(labs[29]))

            add(LabFragment31(labs[30]))
            add(LabFragment32(labs[31]))
            add(LabFragment33(labs[32]))
            add(LabFragment34(labs[33]))
            add(LabFragment35(labs[34]))
            add(LabFragment36(labs[35]))
            add(LabFragment37(labs[36]))
            add(LabFragment38(labs[37]))
            add(LabFragment39(labs[38]))
            add(LabFragment40(labs[39]))

            add(LabFragment41(labs[40]))
            add(LabFragment42(labs[41]))
            add(LabFragment43(labs[42]))
            add(LabFragment44(labs[43]))
            add(LabFragment45(labs[44]))
            add(LabFragment46(labs[45]))
            add(LabFragment47(labs[46]))
        }
    }
}
