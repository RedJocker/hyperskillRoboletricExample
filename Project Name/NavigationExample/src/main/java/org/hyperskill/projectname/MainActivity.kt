package org.hyperskill.projectname

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messageEditText = findViewById<EditText>(R.id.messageEditText)

        findViewById<Button>(R.id.nextActivityButton).setOnClickListener {
            val message = messageEditText.let {
                val msg = it.text.toString()
                it.setText("")
                msg
            }
            val intent = Intent(this@MainActivity, NextActivity::class.java)
            intent.putExtra("MESSAGE", message)
            startActivity(intent)
        }
    }
}