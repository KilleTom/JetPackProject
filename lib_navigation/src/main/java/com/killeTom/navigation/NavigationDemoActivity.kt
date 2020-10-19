package com.killeTom.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NavigationDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_demo)

        val ss = sss()

        ss.s1()
    }
}