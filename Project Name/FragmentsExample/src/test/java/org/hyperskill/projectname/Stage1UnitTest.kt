package org.hyperskill.projectname

import android.view.ViewGroup
import android.widget.Button
import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){

    private val fragment1Button: Button by lazy {
        activity.findViewByString("fragment1Button")
    }

    private val fragment2Button: Button by lazy {
        activity.findViewByString("fragment2Button")
    }

    private val fragmentContainer: ViewGroup by lazy {
        activity.findViewByString("fragmentContainer")
    }


    @Test
    fun testClick() {

        testActivity {
            fragment1Button
            fragment2Button
            fragmentContainer

            fragmentContainer.findViewByString<Button>("myFragmentButton1")
                .clickAndRun()

            fragment2Button.clickAndRun()

            fragmentContainer.findViewByString<Button>("myFragmentButton2")
                .clickAndRun()

            assertLastToastMessageEquals("toast was not shown","on fragment two activityFun with counter on 2")

            fragment1Button.clickAndRun()

            fragmentContainer.findViewByString<Button>("myFragmentButton1").also { button ->
                button.clickAndRun()
                assertEquals("myFragmentButton1 text didn't change", "You", button.text)
            }
        }
    }
}