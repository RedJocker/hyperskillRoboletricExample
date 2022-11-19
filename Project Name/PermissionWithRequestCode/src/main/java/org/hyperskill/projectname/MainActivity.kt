package org.hyperskill.projectname

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.hyperskill.projectname.PermissionHandler.askPermissionToReadExternalStorage
import org.hyperskill.projectname.PermissionHandler.hasPermission
import org.hyperskill.projectname.PermissionHandler.requestCode
import org.hyperskill.projectname.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding:  ActivityMainBinding

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
            askPermissionToReadExternalStorage()
        }
    }

    override fun onRequestPermissionsResult(
        code: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(code, permissions, grantResults)

        if(code == requestCode) {
            grantResults.forEachIndexed { index: Int, result: Int ->
                if (result == PackageManager.PERMISSION_GRANTED) {
                    if(permissions[index] == Manifest.permission.READ_EXTERNAL_STORAGE) {
                        showToast("permission given")
                        mainBinding.someTextView.text = "has permission"
                        mainBinding.permissionButton.callOnClick()
                    }
                } else {
                    showToast("permission not given")
                }
            }

        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}