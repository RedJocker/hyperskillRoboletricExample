package org.hyperskill.projectname.internals

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.widget.Button
import org.junit.Assert.assertEquals
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowNotificationManager

open class NotificationExampleUnitTest<T: Activity>(clazz: Class<T>) : AbstractUnitTest<T>(clazz)  {

    companion object {
        const val CHANNEL_ID = "org.hyperskill"
        const val NOTIFICATION_ID = 393939
    }

    protected val notificationManager: ShadowNotificationManager by lazy {
        Shadows.shadowOf(
            activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        )
    }

    protected val notificationSendButton: Button by lazy {
        val idString = "notificationSendButton"
        val view = activity.findViewByString<Button>(idString)
        view.assertButtonText(buttonIdString = idString, expected = "send notification")
        view
    }

    private fun Button.assertButtonText(buttonIdString: String, expected: String) {
        val message = "For view with id \"$buttonIdString\", in property \"text\""
        assertEquals(message, expected, this.text.toString().lowercase())
    }

}