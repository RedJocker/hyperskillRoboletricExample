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
        activity.findViewByString("state1Button")
    }

    private val fragment2Button: Button by lazy {
        activity.findViewByString("state2Button")
    }

    private val fragmentContainer1: ViewGroup by lazy {
        activity.findViewByString("fragmentContainer1")
    }

    private val fragmentContainer2: ViewGroup by lazy {
        activity.findViewByString("fragmentContainer2")
    }


    @Test
    fun testClick() {

        testActivity {

        }
    }
}