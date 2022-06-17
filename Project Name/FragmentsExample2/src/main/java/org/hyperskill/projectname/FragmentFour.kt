package org.hyperskill.projectname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FragmentFour(private val controller: FragmentsController) : Fragment(), Observer<Int> {

    private val message = "fragment4 with counter: %d"
    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_four, container, false)

        textView = view.findViewById<TextView>(R.id.fragment4MessageTextView).apply {
            text = message.format(controller.with.counter)
        }

        return view
    }

    override fun onNotify(observed: Int) {
        println("onNotify fragment4")
        textView.text = message.format(observed)
    }

}