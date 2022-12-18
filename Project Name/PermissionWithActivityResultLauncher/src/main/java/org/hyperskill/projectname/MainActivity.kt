package org.hyperskill.projectname

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.hyperskill.projectname.PermissionHandler.hasPermission

import org.hyperskill.projectname.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding:  ActivityMainBinding
    val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), this::onPermissionsResult)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.permissionButton.setOnClickListener(this::onPermissionButtonClick)
    }

    private fun onPermissionButtonClick(v: View){
        if(hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
           Snackbar.make(mainBinding.root, "Action with permission", Snackbar.LENGTH_LONG).show()
        } else {
            permissionRequestLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun onPermissionsResult(result: Boolean) {
        if (result) {
            showToast("permission given")
            mainBinding.someTextView.text = "has permission"
            mainBinding.permissionButton.callOnClick()
        } else {
            showToast("permission not given")
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}