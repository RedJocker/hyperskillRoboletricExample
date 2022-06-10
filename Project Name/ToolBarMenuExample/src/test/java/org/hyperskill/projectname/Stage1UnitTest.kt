package org.hyperskill.projectname

import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){

    @Test
    fun testExistingMenuItem() {
        testActivity {
            activity.clickMenuItemAndRun("settingsMenuItem")

            assertLastToastMessageEquals(
                "Toast message was wrong",
                "settings clicked"
            ) // fail
        }
    }

    @Test
    fun testInexistingMenuItem() {
        testActivity {
            activity.clickMenuItemAndRun("settings")  // fail

            assertLastToastMessageEquals(
                "Toast message was wrong",
                "settings clicked"
            ) // fail
        }
    }

    @Test
    fun testIdIsView() {
        testActivity {
            activity.clickMenuItemAndRun("someTextView")

            assertLastToastMessageEquals(
                "Toast message was wrong",
                "settings clicked"
            ) // fail
        }
    }
}