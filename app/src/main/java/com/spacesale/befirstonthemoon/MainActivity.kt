package com.spacesale.befirstonthemoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spacesale.befirstonthemoon.view.globe.GlobeFragment
import com.spacesale.befirstonthemoon.view.planets.SelectFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, GlobeFragment.newInstance(1))
                    .commit()
        }
    }
}