package org.hyperskill.projectname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FragmentThree(private val with: MainActivity) : Fragment(), Observer<Int> {

    private val message = "fragment3 with counter: %d"
    lateinit var textView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_three, container, false)

        textView = view.findViewById<TextView>(R.id.fragment3MessageTextView).apply {
            text = message.format(with.counter)
        }

        return view
    }

    override fun onNotify(observed: Int) {
        println("onNotify fragment3")
        textView.text = message.format(observed)
    }
}