package iticbcn.appmototrack

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import iticbcn.appmototrack.fragments.MotorcycleFragment
import iticbcn.appmototrack.fragments.RemindersFragment
import iticbcn.appmototrack.fragments.StatsFragment
import iticbcn.appmototrack.fragments.HomeFragment
import iticbcn.appmototrack.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<LinearLayout>
    private lateinit var icons: Array<TextView>
    private lateinit var texts: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones
        buttons = arrayOf(
            findViewById(R.id.BtnReminders),
            findViewById(R.id.BtnStats),
            findViewById(R.id.BtnHome),
            findViewById(R.id.BtnMotos),
            findViewById(R.id.BtnAccount)
        )

        // Iconos (se referencian los TextView que contienen el icono superior 'app:drawableTopCompat')
        icons = arrayOf(
            findViewById(R.id.TextReminders),
            findViewById(R.id.TextStats),
            findViewById(R.id.TextHome),
            findViewById(R.id.TextMotos),
            findViewById(R.id.TextAccount)
        )

        // Textos
        texts = arrayOf(
            findViewById(R.id.TextReminders),
            findViewById(R.id.TextStats),
            findViewById(R.id.TextHome),
            findViewById(R.id.TextMotos),
            findViewById(R.id.TextAccount)
        )

        // Navegación
        buttons[0].setOnClickListener {
            selectFragment(RemindersFragment(), 0)
        }

        buttons[1].setOnClickListener {
            selectFragment(StatsFragment(), 1)
        }

        buttons[2].setOnClickListener {
            selectFragment(HomeFragment(), 2)
        }

        buttons[3].setOnClickListener {
            selectFragment(MotorcycleFragment(), 3)
        }

        buttons[4].setOnClickListener {
            selectFragment(SettingsFragment(), 4)
        }

        // Fragment inicial
        if (savedInstanceState == null) {
            selectFragment(HomeFragment(), 2)
        }
    }

    fun selectFragment(fragment: Fragment, selected: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        for (i in buttons.indices) {
            buttons[i].isSelected = (i == selected)
        }
    }


}