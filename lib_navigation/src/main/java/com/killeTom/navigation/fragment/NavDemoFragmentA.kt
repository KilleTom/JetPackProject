package com.killeTom.navigation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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

            var bundle: Bundle = bundleOf("value" to "hello")

            findNavController()
                .navigate(R.id.action_fragmentA_to_fragmentB,bundle)
//            Fragment.findNavController(this).navigate(R.id.action_fragmentA_to_fragmentB)
        }

        return view
    }


    override fun onResume() {
        super.onResume()

        resultAction()
    }

    private fun resultAction(){
        val bundle = arguments?:return

        val args = NavDemoFragmentCArgs.fromBundle(bundle)

        Log.i(this::class.java.simpleName,args.toString())

    }
}