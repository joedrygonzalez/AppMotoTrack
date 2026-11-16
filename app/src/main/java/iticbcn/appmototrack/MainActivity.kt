package iticbcn.appmototrack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import iticbcn.appmototrack.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHome = findViewById<View>(R.id.BtnHome)

        if (savedInstanceState == null) {
            btnHome.isSelected = true
            loadFragment(HomeFragment())
        }

        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}