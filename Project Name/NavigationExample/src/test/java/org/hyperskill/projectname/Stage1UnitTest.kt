package org.hyperskill.projectname

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.manifest.AndroidManifest
import org.robolectric.manifest.IntentFilterData
import org.robolectric.res.Fs
import java.io.File


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){

    val messageEditText by lazy {
        activity.findViewByString<EditText>("messageEditText")
    }

    val nextActivityButton by lazy {
        activity.findViewByString<Button>("nextActivityButton")
    }

    @Test
    fun testLauncher() {
        val relativePathString = "src/main/AndroidManifest.xml"
        val absolutePathString = File(relativePathString).absolutePath
        val manifestPath = Fs.fromUrl(absolutePathString)
        val appManifest = AndroidManifest(manifestPath, null, null)

        val activityData = appManifest.getActivityData("org.hyperskill.projectname.MainActivity")
        val intentFilters: MutableList<IntentFilterData> = activityData.intentFilters
        val data = intentFilters.getOrNull(0)
            ?: throw AssertionError("No intent filter declared for MainActivity on AndroidManifest.xml")


        assertTrue("Wrong manifest intent-filter property for MainActivity, expected to contain action android.intent.action.MAIN",
            data.actions.contains("android.intent.action.MAIN")
        )
        assertTrue("Wrong manifest intent-filter property for MainActivity, expected to contain category android.intent.category.LAUNCHER",
            data.categories.contains("android.intent.category.LAUNCHER")
        )
    }

    @Test
    fun testNavigationWithExtra() {
        val nextActivityIntent = testActivity {
            nextActivityButton
            messageEditText

            messageEditText.setText("I'm a message from MainActivity")
            nextActivityButton.clickAndRun()

            val nextStartedActivityIntent = shadowActivity.nextStartedActivity
                ?: throw AssertionError("No Intent for starting activity found")

            val expectedStartedActivityShortClassName = ".NextActivity"
            val actualStartedActivityShortClassName =
                nextStartedActivityIntent.component?.shortClassName ?: "null"
            assertEquals(
                "Next Activity was not the expected",
                expectedStartedActivityShortClassName,
                actualStartedActivityShortClassName
            )
            return@testActivity nextStartedActivityIntent
        }

        activityController.pause()
        activityController.stop()
        val nextActivityUnityTest = object : AbstractUnitTest<NextActivity>(NextActivity::class.java){}

        nextActivityUnityTest.testActivity(nextActivityIntent) { nextActivity ->
            val messageDisplay = nextActivity.findViewByString<TextView>("displayMessageTextView")


            val expectedMessage = "Hello NextActivity, here is the message from MainActivity:\n" +
                    "I'm a message from MainActivity"
            val actualMessage = messageDisplay.text.toString()
            assertEquals("Wrong message displayed", expectedMessage, actualMessage)
        }
    }
}