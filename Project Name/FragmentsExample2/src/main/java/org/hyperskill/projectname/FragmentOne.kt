package org.hyperskill.projectname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentOne(private val controller: FragmentsController) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)

        view.findViewById<Button>(R.id.fragment1Button).setOnClickListener { view ->
            controller.activityFun1("on fragment one", (view as Button))
            controller.notifyFragment1Click()
        }

        return view
    }

}