package com.killeTom.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.NavHostFragment
import com.killeTom.navigation.R
import kotlinx.android.synthetic.main.nav_demo_fragment_c.view.*

class NavDemoFragmentC :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.nav_demo_fragment_c, container, false)


        view.nav_action.setOnClickListener {

            val bundle = bundleOf(
                "message" to "ok",
                "result" to true
            )

            val args = NavDemoFragmentCArgs.fromBundle(bundle)


            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentC_to_fragmentA,args.toBundle())
        }

        return view
    }

}