package com.killeTom.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.killeTom.navigation.R
import kotlinx.android.synthetic.main.nav_demo_fragment_b.view.*

class NavDemoFragmentB :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.nav_demo_fragment_b, container, false)


        view.nav_action.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentB_to_fragmentC)
        }

        return view
    }
}