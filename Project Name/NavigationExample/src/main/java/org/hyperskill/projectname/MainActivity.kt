package org.hyperskill.projectname

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        println(TAG +  " onCreate called")
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

    override fun onStart() {
        println(TAG + " onStart called")
        super.onStart()
    }

    override fun onResume() {
        println(TAG +  " onResume called")
        super.onResume()
    }

    override fun onRestart() {
        println(TAG +  " onRestart called")
        super.onRestart()
    }

    override fun onPause() {
        println(TAG +  " onPause called")
        super.onPause()
    }

    override fun onStop() {
        println(TAG +  " onStop called")
        super.onStop()
    }

    override fun onDestroy() {
        println(TAG + " onDestroy called")
        super.onDestroy()
    }
}