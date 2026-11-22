package iticbcn.appmototrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import iticbcn.appmototrack.MainActivity
import iticbcn.appmototrack.R
import iticbcn.appmototrack.adapters.RemindersAdapter

class MotorcycleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_motorcycle,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.CV_help)?.setOnClickListener {
            (activity as? MainActivity)?.selectFragment(HelpFragment(), -1)
        }

        val sampleReminders = listOf(
            "Aceite motor",
            "Freno delantero",
            "Freno trasero",
            "Neumáticos",
            "Cadena"
        )
        val rvReminders = view.findViewById<RecyclerView>(R.id.rvMotorcycleReminders)
        rvReminders?.adapter = RemindersAdapter(sampleReminders)
    }
}