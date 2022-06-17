package org.hyperskill.projectname

import android.widget.Button
import android.widget.Toast

class FragmentsController(val with: MainActivity) {

    //state 1
    private val fragmentOne = FragmentOne(this)
    private val fragmentThree = FragmentThree(this)

    //state 2
    private val fragmentTwo = FragmentTwo(this)
    private val fragmentFour = FragmentFour(this)

    private val fragmentOneObservers = setOf<Observer<Int>>(fragmentThree)
    private val fragmentTwoObservers = setOf<Observer<Int>>(fragmentFour)

    fun changeComponentsDisplayedState(state: Int) {
        val (toContainer1, toContainer2) = when(state) {
            2 -> fragmentTwo to fragmentFour
            else -> fragmentOne to fragmentThree
        }

        with.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer1, toContainer1)
            .replace(R.id.fragmentContainer2, toContainer2)
            .commit()
    }

    fun activityFun2(message: String) {
        with.counter++
        val message = "$message activityFun with counter on ${with.counter}"
        println(message)
        Toast.makeText(with, message, Toast.LENGTH_LONG).show()
    }

    fun activityFun1(message: String, fragmentOneButton: Button) {
        with.counter++
        val message = "$message activityFun with counter on ${with.counter}"
        println(message)
        Toast.makeText(with, message, Toast.LENGTH_LONG).show()
        fragmentOneButton.text = "You changed"
    }

    fun notifyFragment1Click() {
        fragmentOneObservers.forEach(this::notify)
    }

    fun notifyFragment2Click() {
        fragmentTwoObservers.forEach(this::notify)
    }

    fun notify(observer:  Observer<Int>) {
        observer.onNotify(with.counter)
    }
}