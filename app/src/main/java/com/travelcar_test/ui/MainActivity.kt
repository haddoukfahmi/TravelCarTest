package com.travelcar_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.travelcar_test.R
import com.travelcar_test.adapter.TabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = TabsAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabs.setupWithViewPager(viewPager)
    }
}
