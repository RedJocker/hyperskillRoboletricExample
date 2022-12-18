package org.hyperskill.projectname

import android.Manifest
import android.content.pm.PackageManager
import org.hyperskill.projectname.internals.CustomShadowSnackbar
import org.hyperskill.projectname.internals.CustomShadowSnackbar.Companion.getTextMessage
import org.hyperskill.projectname.internals.PermissionExampleUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit


@RunWith(RobolectricTestRunner::class)
@Config(shadows = [CustomShadowSnackbar::class])
class Stage1UnitTest : PermissionExampleUnitTest<MainActivity>(MainActivity::class.java){


    @Test
    fun testPermissionGrantedAfterRequestingPermission() {

        testActivity {

            permissionButton.clickAndRun()

            assertRequestPermissions(
                permissionsRequired = listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            )
            val requestedPermission = shadowActivity.lastRequestedPermission

            shadowActivity.grantPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            activity.onRequestPermissionsResult(
                requestedPermission.requestCode,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                intArrayOf(PackageManager.PERMISSION_GRANTED)
            )

            assertLastToastMessageEquals(
                "Wrong Toast message after permission given",
                "permission given"
            )

            val snackbar = CustomShadowSnackbar.shownSnackbars.lastOrNull()
                ?: throw AssertionError("No snackbar was found after permission is given")

            val actualMessage = snackbar.getTextMessage()

            assertEquals (
                "After permission is given the action with permission should be triggered without requiring extra clicks",
                "Action with permission",
                actualMessage
            )

            CustomShadowSnackbar.clear()

            permissionButton.clickAndRun()

            val snackbar2 = CustomShadowSnackbar.shownSnackbars.lastOrNull()
                ?: throw AssertionError("No snackbar was found after permissionButton clicked already having permission")

            val actualMessage2 = snackbar2.getTextMessage()

            assertEquals (
                "Clicking on permissionButton with permission already given should trigger action with permission",
                "Action with permission",
                actualMessage2
            )
        }
    }

    @Test
    fun testPermissionNotGrantedAfterRequestingPermission() {

        testActivity {

            permissionButton.clickAndRun()

            assertRequestPermissions(
                permissionsRequired = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            )
            val requestedPermission = shadowActivity.lastRequestedPermission

            activity.onRequestPermissionsResult(
                requestedPermission.requestCode,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                intArrayOf(PackageManager.PERMISSION_DENIED)
            )

            assertLastToastMessageEquals(
                "Wrong Toast message after permission given",
                "permission not given"
            )
        }
    }
}