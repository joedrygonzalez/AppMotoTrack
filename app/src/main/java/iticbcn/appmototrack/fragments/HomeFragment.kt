package iticbcn.appmototrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import iticbcn.appmototrack.MainActivity
import iticbcn.appmototrack.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as? MainActivity

        view.findViewById<CardView>(R.id.CV_help)?.setOnClickListener {
            mainActivity?.selectFragment(HelpFragment(), -1)
        }

        view.findViewById<CardView>(R.id.CV_StatsMainMenu)?.setOnClickListener {
            mainActivity?.selectFragment(StatsFragment(), 1)
        }

        view.findViewById<CardView>(R.id.CV_RemainderMainMenu)?.setOnClickListener {
            mainActivity?.selectFragment(RemindersFragment(), 0)
        }

        view.findViewById<CardView>(R.id.CV_SettingsMainMenu)?.setOnClickListener {
            mainActivity?.selectFragment(SettingsFragment(), 4)
        }

        view.findViewById<CardView>(R.id.CV_MotorcycleMainMenu)?.setOnClickListener {
            mainActivity?.selectFragment(MotorcycleFragment(), 3)
        }
    }
}