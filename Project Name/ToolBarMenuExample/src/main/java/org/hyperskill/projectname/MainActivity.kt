package org.hyperskill.projectname

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.someTextView).setOnClickListener {
            println("someTextView clicked")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favoritesMenuItem -> showToast("favorites clicked")
            R.id.settingsMenuItem -> showToast("settings clicked")
            R.id.FeedbackMenuItem -> showToast("feedback clicked")
            R.id.CloseMenuItem -> showToast("close clicked")
        }
        return true
    }

    private fun showToast(message: String) {
        println(message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

