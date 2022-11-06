package org.hyperskill.projectname

import android.app.TimePickerDialog
import android.widget.Button
import android.widget.TextView
import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowDialog
import java.util.concurrent.TimeUnit


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){


    private val timeTv by lazy {
        activity.findViewByString<TextView>("timeTv")
    }

    private val pickTimeBtn by lazy {
        activity.findViewByString<Button>("pickTimeBtn")
    }


    @Test
    fun testSomething() {

        testActivity {

            pickTimeBtn.clickAndRun()

            val timePickerDialog = getLatestTimePickerDialog()
            timePickerDialog.pickTime(23, 10)

            val expectedTimeText = "11:10 pm"
            val actualTimeText = timeTv.text.toString()

            assertEquals("some error message", expectedTimeText, actualTimeText)
        }
    }


    /**
     * Use this method to retrieve the last TimePickerDialog shown.
     *
     * If no TimePickerDialog was found it throws an AssertionError with message notFoundMessage,
     * notFoundMessage has a default value of "No TimePickerDialog was found"
     */

    private fun getLatestTimePickerDialog(notFoundMessage: String = "No TimePickerDialog was found"): TimePickerDialog {
        return ShadowDialog.getShownDialogs().mapNotNull {
            if(it is TimePickerDialog) it else null
        }.lastOrNull() ?: throw AssertionError("No TimePickerDialog found")
    }


    /**
     * Use this method to pick a time on a TimePickerDialog.
     *
     * The Dialog will be dismissed after this.
     *
     * It will also advance the clock advanceClockMillis milliseconds and run enqueued Runnable scheduled to run on main looper in that timeframe.
     * Default value for advanceClockMillis is 500
     */
    private fun TimePickerDialog.pickTime(hourOfDay: Int, minuteOfHour: Int, advanceClockMillis: Long = 500) {
        val shadowTimePickerDialog = shadowOf(this)

        this.updateTime(hourOfDay, minuteOfHour)
        shadowTimePickerDialog.clickOn(android.R.id.button1) // ok button
        shadowLooper.idleFor(advanceClockMillis, TimeUnit.MILLISECONDS)
    }
}