package org.hyperskill.projectname

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, FragmentOne(this))
            .commit()

        findViewById<Button>(R.id.fragment1Button).setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FragmentOne(this))
                .commit()
        }


        findViewById<Button>(R.id.fragment2Button).setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FragmentTwo(this))
                .commit()
        }
    }

    fun activityFun2(message: String) {
        counter++
        val message = "$message activityFun with counter on $counter"
        println(message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun activityFun1(message: String, fragmentOneButton: Button) {
        counter++
        val message = "$message activityFun with counter on $counter"
        println(message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        fragmentOneButton.text = "You changed"
    }
}