package by.gsu.siemeljanov.labs

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem
import kotlinx.android.synthetic.main.fragment_lab8.*
import java.util.*

class LabFragment8(private val lab: ListItem) : Fragment() {

    private val PERMISSIONS_REQUEST_CAMERA = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab8, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text1.text = lab.title
        button.setOnClickListener {
            open()
        }
        open()
    }

    private fun open() {
        if (checkSelfPermission(context!!, Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
            if (getFromPref(
                    context!!,
                    getString(R.string.allowed).toUpperCase(Locale.getDefault())
                )!!
            ) {
                showSettingsAlert()
            } else if (checkSelfPermission(
                    context!!,
                    Manifest.permission.CAMERA
                ) != PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.CAMERA
                    )
                ) {
                    showAlert()
                } else {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.CAMERA),
                        PERMISSIONS_REQUEST_CAMERA
                    )
                }
            }
        } else {
            openCamera()
        }
    }

    private fun saveToPreferences(context: Context, key: String?, allowed: Boolean?) {
        context.getSharedPreferences(getString(R.string.camera_pref), Context.MODE_PRIVATE)
            .edit().putBoolean(key, allowed!!).apply()
    }

    private fun getFromPref(context: Context, key: String?): Boolean? {
        return context.getSharedPreferences(getString(R.string.camera_pref), Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(context!!).create()
        dialog.setTitle(getString(R.string.alert))
        dialog.setMessage(getString(R.string.text_camera_access))
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.dont_allow)) { dlg, _ ->
            dlg.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.allow)) { dlg, _ ->
            dlg.dismiss()
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSIONS_REQUEST_CAMERA
            )
        }
        dialog.show()
    }

    private fun showSettingsAlert() {
        val dialog = AlertDialog.Builder(context!!).create()
        dialog.setTitle(getString(R.string.alert))
        dialog.setMessage(getString(R.string.text_camera_access))
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.dont_allow)) { dlg, _ ->
            dlg.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.settings)) { dlg, _ ->
            dlg.dismiss()
            startInstalledAppDetailsActivity(context as Activity?)
        }
        dialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CAMERA -> {
                for (i in permissions.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                context as Activity,
                                permissions[i]!!
                            )
                        ) {
                            showAlert()
                        } else {
                            saveToPreferences(
                                context as Activity,
                                getString(R.string.allowed).toUpperCase(Locale.getDefault()),
                                true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun startInstalledAppDetailsActivity(context: Activity?) {
        if (context == null) {
            return
        }
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = Uri.parse("package:" + context.packageName)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        context.startActivity(intent)
    }

    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }
}
