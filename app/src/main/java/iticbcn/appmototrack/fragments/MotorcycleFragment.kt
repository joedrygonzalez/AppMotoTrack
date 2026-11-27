
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
import android.widget.TextView
import iticbcn.appmototrack.utils.DateUtils

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

        // Botón de ayuda
        view.findViewById<CardView>(R.id.CV_help)?.setOnClickListener {
            (activity as? MainActivity)?.selectFragment(HelpFragment(), -1)
        }

        // Botón de edición
        view.findViewById<CardView>(R.id.CV_edit)?.setOnClickListener {
            EditMotorcycleFragment().show(parentFragmentManager, "EditMotorcycle")
        }

        // Recordatorios
        val today = DateUtils.getCurrentDateFormatted()

        val sampleReminders = listOf(
            Reminder(1, "aceite motor", today),
            Reminder(2, "freno delantero", today),
            Reminder(3, "freno trasero", today),
            Reminder(4, "neumáticos", today),
            Reminder(5, "cadena", today)
        )

        val rvReminders =
            view.findViewById<RecyclerView>(R.id.rvMotorcycleReminders)

        rvReminders?.adapter = RemindersAdapter(sampleReminders)

        parentFragmentManager.setFragmentResultListener(
            "motorcycleUpdated",
            viewLifecycleOwner
        ) { _, _ ->
            onResume()
        }
    }
    override fun onResume() {
        super.onResume()

        val preferences = requireContext().getSharedPreferences(
            "MotorcycleData",
            android.content.Context.MODE_PRIVATE
        )

        val view = view ?: return

        if (preferences.contains("totalKm")) {
            view.findViewById<TextView>(R.id.tvTotalKm)
                .text = preferences.getString("totalKm", "").takeUnless { it.isNullOrBlank() } ?: "0000"
        }

        if (preferences.contains("tripA")) {
            view.findViewById<TextView>(R.id.tvTripA)
                .text = preferences.getString("tripA", "").takeUnless { it.isNullOrBlank() } ?: "- - - -"
        }

        if (preferences.contains("tripB")) {
            view.findViewById<TextView>(R.id.tvTripB)
                .text = preferences.getString("tripB", "").takeUnless { it.isNullOrBlank() } ?: "- - - -"
        }

        if (preferences.contains("oilKm")) {
            view.findViewById<TextView>(R.id.tvOilKm)
                .text = preferences.getString("oilKm", "").takeUnless { it.isNullOrBlank() } ?: "- - - -"
        }

        if (preferences.contains("rearBrakeKm")) {
            view.findViewById<TextView>(R.id.tvRearBrakeKm)
                .text = preferences.getString("rearBrakeKm", "").takeUnless { it.isNullOrBlank() } ?: "- - - -"
        }

        if (preferences.contains("frontBrakeKm")) {
            view.findViewById<TextView>(R.id.tvFrontBrakeKm)
                .text = preferences.getString("frontBrakeKm", "").takeUnless { it.isNullOrBlank() } ?: "- - - -"
        }
    }
}
