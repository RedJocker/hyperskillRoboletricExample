package org.hyperskill.projectname

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Button
import android.widget.TextView
import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){

    private val counterTextView by lazy {
        activity.findViewByString<TextView>("counterTextView")
    }

    private val incrementCounterButton by lazy {
        activity.findViewByString<Button>("incrementCounterButton")
    }

    private val sharedPreferences: SharedPreferences by lazy {
        RuntimeEnvironment.getApplication().getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}_sharedPreferences",
            MODE_PRIVATE
        )
    }

    private val counterKey = "MainActivity.counter"

    @Test
    fun testPersistence() {

        testActivity {
            counterTextView
            incrementCounterButton
            sharedPreferences

            assertEquals(
                "initial messageEditText",
                "0",
                counterTextView.text.toString()
            )

            incrementCounterButton.clickAndRun()

            assertEquals(
                "after one click messageEditText",
                "1",
                counterTextView.text.toString()
            )


            val actualPersistedValue = sharedPreferences.getInt(counterKey, 0)
            assertEquals("persisted value", 1, actualPersistedValue)
        }
    }

    @Test
    fun testRestorePersistedValue() {

        sharedPreferences.edit().putInt(counterKey, 1).commit()

        testActivity {
            counterTextView
            incrementCounterButton

            assertEquals(
                "after recreate messageEditText",
                "0",
                counterTextView.text.toString()
            )
        }
    }
}