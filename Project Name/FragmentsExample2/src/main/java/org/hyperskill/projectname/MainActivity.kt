package org.hyperskill.projectname

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    var counter = 0

    private val fragmentOne = FragmentOne(this)
    private val fragmentTwo = FragmentTwo(this)
    private val fragmentThree = FragmentThree(this)
    private val fragmentFour = FragmentFour(this)

    private val fragmentOneObservers = setOf<Observer<Int>>(fragmentThree)
    private val fragmentTwoObservers = setOf<Observer<Int>>(fragmentFour)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer1, fragmentOne)
            .add(R.id.fragmentContainer2, fragmentThree)
            .commit()

        findViewById<Button>(R.id.state1Button).setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer1, fragmentOne)
                .replace(R.id.fragmentContainer2, fragmentThree)
                .commit()
        }

        findViewById<Button>(R.id.state2Button).setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer1, fragmentTwo)
                .replace(R.id.fragmentContainer2, fragmentFour)
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

    fun notifyFragment1Click() {
        fragmentOneObservers.forEach(this::notify)
    }

    fun notifyFragment2Click() {
        fragmentTwoObservers.forEach(this::notify)
    }

    fun notify(observer:  Observer<Int>) {
            observer.onNotify(counter)
    }
}