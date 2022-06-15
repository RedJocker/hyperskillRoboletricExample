package org.hyperskill.projectname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentOne(val with: MainActivity) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)

        view.findViewById<Button>(R.id.myFragmentButton1).setOnClickListener { view ->
            with.activityFun1("on fragment one", (view as Button))
        }

        return view
    }

}