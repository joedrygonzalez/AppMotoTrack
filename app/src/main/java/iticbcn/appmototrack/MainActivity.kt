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

        val BtnReminders = findViewById<View>(R.id.BtnReminders)
        val BtnStats = findViewById<View>(R.id.BtnStats)
        val BtnHome = findViewById<View>(R.id.BtnHome)
        val BtnMotos = findViewById<View>(R.id.BtnMotos)
        val BtnAccount = findViewById<View>(R.id.BtnAccount)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        BtnHome.setOnClickListener {
            loadFragment(HomeFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}