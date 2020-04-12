package by.gsu.siemeljanov.fragments.lab20

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.HttpHandler
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab18.*
import org.json.JSONException
import org.json.JSONObject

class LabFragment18(private val lab: ListItem) : Fragment() {

    lateinit var contactList: ArrayList<HashMap<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab18, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        contactList = ArrayList()
        GetContacts().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetContacts : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val sh = HttpHandler()
            val url = "https://api.androidhive.info/contacts/"
            val jsonStr: String? = sh.makeServiceCall(url)
            if (jsonStr != null) {
                try {
                    val jsonObj = JSONObject(jsonStr)
                    val contacts = jsonObj.getJSONArray("contacts")
                    for (i in 0 until contacts.length()) {
                        val c = contacts.getJSONObject(i)
                        val id = c.getString("id")
                        val name = c.getString("name")
                        val email = c.getString("email")
                        val address = c.getString("address")
                        val gender = c.getString("gender")
                        val phone = c.getJSONObject("phone")
                        val mobile = phone.getString("mobile")
                        val home = phone.getString("home")
                        val office = phone.getString("office")
                        val contact: HashMap<String, String> = HashMap()
                        contact["id"] = id
                        contact["name"] = name
                        contact["email"] = email
                        contact["mobile"] = mobile
                        contactList.add(contact)
                    }
                } catch (e: JSONException) {
                    activity?.runOnUiThread { toast("${getString(R.string.text_error)} ${e.message}") }
                }
            } else {
                activity?.runOnUiThread { toast(getString(R.string.text_could_not_get)) }
            }
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()
            toast(getString(R.string.text_downloading))
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            list.adapter = SimpleAdapter(
                context,
                contactList,
                R.layout.list_item_lab18,
                arrayOf("email", "mobile"),
                intArrayOf(R.id.email, R.id.mobile)
            )
        }
    }

    private fun toast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}
