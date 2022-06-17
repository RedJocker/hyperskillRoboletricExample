package org.hyperskill.projectname

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.hyperskill.projectname.internals.AbstractUnitTest
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
    fun testInitializeState1() {

        testActivity {
            fragmentContainer1
            fragmentContainer2


            fragmentContainer1.findViewByString<Button>("fragment1Button")
            fragmentContainer2.findViewByString<TextView>("fragment3MessageTextView")
        }
    }

    @Test
    fun testFailInitializeState2() {

        testActivity {
            fragmentContainer1
            fragmentContainer2


            fragmentContainer1.findViewByString<Button>("fragment2Button")  // fails
            fragmentContainer2.findViewByString<TextView>("fragment4MessageTextView")
        }
    }

    @Test
    fun moreTestsTodo() {
        // Todo(launch the example, observe its functionality and make assertions)
    }
}