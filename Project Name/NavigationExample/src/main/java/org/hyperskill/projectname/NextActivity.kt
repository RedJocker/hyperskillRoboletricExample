package org.hyperskill.projectname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val messageRaw = intent.extras?.getString("MESSAGE")

        val message = if(messageRaw == null || messageRaw.isBlank()) {
            "there are no messages from MainActivity"
        } else {
            messageRaw
        }

        findViewById<TextView>(R.id.displayMessageTextView).apply {
            text = "Hello NextActivity, here is the message from MainActivity:\n$message"
        }
    }
}