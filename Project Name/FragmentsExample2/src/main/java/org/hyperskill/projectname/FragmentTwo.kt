package org.hyperskill.projectname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentTwo(private val controller: FragmentsController) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_two, container, false)

        view.findViewById<Button>(R.id.fragment2Button).setOnClickListener {
            controller.activityFun2("on fragment two")
            controller.notifyFragment2Click()
        }
        return view
    }
}