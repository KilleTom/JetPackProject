package com.killeTom.jetpackproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.killeTom.navigation.NavigationDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_demo.setOnClickListener {
            startActivity(Intent(this,NavigationDemoActivity::class.java))
        }
    }
}