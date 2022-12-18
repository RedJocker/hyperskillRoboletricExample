package org.hyperskill.projectname.internals

import android.app.Activity
import android.widget.Button

open class PermissionExampleUnitTest<T : Activity>(clazz: Class<T>): AbstractUnitTest<T>(clazz) {

    companion object {
        const val requestCode = 1
    }

    val permissionButton by lazy {
        activity.findViewByString<Button>("permissionButton")
    }

}