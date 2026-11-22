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

class RemindersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_remainders,
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
            "Cambio de aceite de motor",
            "Revisión de líquido de frenos delantero",
            "Revisión de líquido de frenos trasero",
            "Inspección de presión de neumáticos",
            "Tensado y engrase de cadena",
            "Cambio de filtro de aire",
            "Revisión técnica anual (ITV)"
        )
        val rvReminders = view.findViewById<RecyclerView>(R.id.rvReminders)
        rvReminders?.adapter = RemindersAdapter(sampleReminders)
    }
}