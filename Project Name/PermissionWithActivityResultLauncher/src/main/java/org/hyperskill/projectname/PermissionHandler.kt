package org.hyperskill.projectname

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.PermissionChecker

object PermissionHandler {

    fun Activity.hasPermission(manifestPermission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.checkSelfPermission(manifestPermission) == PackageManager.PERMISSION_GRANTED
        } else {
            PermissionChecker.checkSelfPermission(this, manifestPermission) == PermissionChecker.PERMISSION_GRANTED
        }
    }
}