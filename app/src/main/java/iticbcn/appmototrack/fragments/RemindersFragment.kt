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
import iticbcn.appmototrack.models.Reminder
import iticbcn.appmototrack.utils.DateUtils

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

        val today = DateUtils.getCurrentDateFormatted()
        val sampleReminders = listOf(
            Reminder(1, "cambio de aceite de motor", today),
            Reminder(2, "revisión de líquido de frenos delantero", today),
            Reminder(3, "revisión de líquido de frenos trasero", today),
            Reminder(4, "inspección de presión de neumáticos", today),
            Reminder(5, "tensado y engrase de cadena", today),
            Reminder(6, "cambio de filtro de aire", today),
            Reminder(7, "revisión técnica anual (ITV)", today)
        )
        val rvReminders = view.findViewById<RecyclerView>(R.id.rvReminders)
        rvReminders?.adapter = RemindersAdapter(sampleReminders)
    }
}