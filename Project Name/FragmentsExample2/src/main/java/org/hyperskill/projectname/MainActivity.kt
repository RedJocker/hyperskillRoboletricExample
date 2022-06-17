package org.hyperskill.projectname

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var counter = 0
    lateinit var fragmentsController: FragmentsController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentsController = FragmentsController(this)

        fragmentsController.changeComponentsDisplayedState(1)

        findViewById<Button>(R.id.state1Button).setOnClickListener {
           fragmentsController.changeComponentsDisplayedState(1)
        }

        findViewById<Button>(R.id.state2Button).setOnClickListener {
            fragmentsController.changeComponentsDisplayedState(2)
        }
    }


}