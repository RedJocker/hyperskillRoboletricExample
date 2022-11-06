package org.hyperskill.projectname

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var previewSelectedTimeTextView: TextView


    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            // logic to properly handle
            // the picked timings by user
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }

            previewSelectedTimeTextView.text = formattedTime
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // instance of the UI elements
        val buttonPickTime: Button = findViewById<Button>(R.id.pickTimeBtn)
        previewSelectedTimeTextView = findViewById<TextView>(R.id.timeTv)

        // handle the pick time button to
        // open the TimePickerDialog
        buttonPickTime.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                this,
                timePickerDialogListener,
                12,
                10,
                false
            )
            timePicker.show()
        }
    }
}