package org.hyperskill.projectname

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.drawable.Icon
import org.hyperskill.projectname.internals.NotificationExampleUnitTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : NotificationExampleUnitTest<MainActivity>(MainActivity::class.java){


    @Test
    fun checkNotificationIsSent() {
        testActivity {
            notificationSendButton.clickAndRun()

            val notificationChannel =
                notificationManager.notificationChannels.mapNotNull {
                    it as NotificationChannel?
                }.firstOrNull {
                    it.id == CHANNEL_ID
                }


            assertNotNull("Could not find any NotificationChannel with id \"$CHANNEL_ID\"",
                notificationChannel)
            notificationChannel!!

            assertTrue(
                "Wrong importance for NotificationChannel, should be IMPORTANCE_HIGH",
                NotificationManager.IMPORTANCE_HIGH == notificationChannel.importance
            )

            val notification: Notification? = notificationManager.getNotification(NOTIFICATION_ID)

            val messageNotificationId =
                "Could not find notification with id 393939. Did you set the proper id?"
            assertNotNull(messageNotificationId, notification)
            notification!!

            val messageChannelId = "The notification channel id does not equals \"$CHANNEL_ID\""
            val actualChannelId = notification.channelId
            assertEquals(messageChannelId, CHANNEL_ID, actualChannelId)

            val messageIcon = "Have you set the notification smallIcon?"
            val actualIcon: Icon? = notification.smallIcon
            assertNotNull(messageIcon, actualIcon)

            val messageTitle = "Have you set the notification title?"
            val actualTitle = notification.extras.getCharSequence(Notification.EXTRA_TITLE)?.toString()
            assertNotNull(messageTitle, actualTitle)

            val messageContent = "Have you set the notification content?"
            val actualContent = notification.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString()
            assertNotNull(messageContent, actualContent)

            val messageOnlyOnce = "Have you set the notification to only alert once?"
            val expectedOnlyOnceFlags = Notification.FLAG_ONLY_ALERT_ONCE
            val actualOnlyOnceFlags = notification.flags.and(Notification.FLAG_ONLY_ALERT_ONCE)
            assertTrue(messageOnlyOnce, expectedOnlyOnceFlags == actualOnlyOnceFlags)

            val messageInsistent = "Have you set the notification to be insistent?"
            val expectedInsistentFlags = Notification.FLAG_INSISTENT
            val actualInsistentFlags = notification.flags.and(Notification.FLAG_INSISTENT)
            assertTrue(messageInsistent, expectedInsistentFlags == actualInsistentFlags)

            notification.contentIntent
        }
    }
}