package com.killeTom.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.killeTom.navigation.R
import kotlinx.android.synthetic.main.nav_demo_fragment_a.view.*

class NavDemoFragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.nav_demo_fragment_a, container, false)

        view.nav_action.setOnClickListener {

            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
//            Fragment.findNavController(this).navigate(R.id.action_fragmentA_to_fragmentB)
        }

        return view
    }


}