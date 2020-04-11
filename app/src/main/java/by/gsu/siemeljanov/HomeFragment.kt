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

        fragments.add(LabFragment11(labs[10]))
        fragments.add(LabFragment12(labs[11]))
        fragments.add(LabFragment13(labs[12]))
        fragments.add(LabFragment14(labs[13]))
        fragments.add(LabFragment15(labs[14]))
        fragments.add(LabFragment16(labs[15]))
        fragments.add(LabFragment17(labs[16]))
        fragments.add(LabFragment18(labs[17]))
        fragments.add(LabFragment19(labs[18]))
        fragments.add(LabFragment20(labs[19]))

        fragments.add(LabFragment21(labs[20]))
        fragments.add(LabFragment22(labs[21]))
        fragments.add(LabFragment23(labs[22]))
        fragments.add(LabFragment24(labs[23]))
        fragments.add(LabFragment25(labs[24]))
        fragments.add(LabFragment26(labs[25]))
        fragments.add(LabFragment27(labs[26]))
        fragments.add(LabFragment28(labs[27]))
        fragments.add(LabFragment29(labs[28]))
        fragments.add(LabFragment30(labs[29]))

        fragments.add(LabFragment31(labs[30]))
        fragments.add(LabFragment32(labs[31]))
        fragments.add(LabFragment33(labs[32]))
        fragments.add(LabFragment34(labs[33]))
        fragments.add(LabFragment35(labs[34]))
        fragments.add(LabFragment36(labs[35]))
        fragments.add(LabFragment37(labs[36]))
        fragments.add(LabFragment38(labs[37]))
        fragments.add(LabFragment39(labs[38]))
        fragments.add(LabFragment40(labs[39]))

        fragments.add(LabFragment41(labs[40]))
        fragments.add(LabFragment42(labs[41]))
        fragments.add(LabFragment43(labs[42]))
        fragments.add(LabFragment44(labs[43]))
        fragments.add(LabFragment45(labs[44]))
        fragments.add(LabFragment46(labs[45]))
        fragments.add(LabFragment47(labs[46]))
    }
}
