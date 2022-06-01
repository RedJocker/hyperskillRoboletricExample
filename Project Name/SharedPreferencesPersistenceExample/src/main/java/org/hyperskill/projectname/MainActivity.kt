package org.hyperskill.projectname

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private val counterKey = "MainActivity.counter"
    private val sharedPreferences by lazy {
        getSharedPreferences("${BuildConfig.APPLICATION_ID}_sharedPreferences", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreCounter()
        initViews()
    }

    private fun initViews() {
        refreshCounter()

        findViewById<Button>(R.id.incrementCounterButton).setOnClickListener {
            refreshCounter { counter++ }
            persistCounter()
        }
    }

    private fun refreshCounter(beforeRefreshOnUiThread: () -> Unit = {}) {
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        runOnUiThread {
            beforeRefreshOnUiThread()
            counterTextView.text = "$counter"
        }
    }

    private fun persistCounter(){
        sharedPreferences.edit().putInt(counterKey, counter).apply()
    }

    private fun restoreCounter() {
        counter = sharedPreferences.getInt(counterKey, 0)
    }
}