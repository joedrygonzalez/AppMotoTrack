
package iticbcn.appmototrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import iticbcn.appmototrack.R

class EditMotorcycleFragment : BottomSheetDialogFragment() {

    private val preferences by lazy {
        requireContext().getSharedPreferences(
            "MotorcycleData",
            android.content.Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_edit_motorcycle,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalKm = view.findViewById<EditText>(R.id.etTotalKm)
        val tripA = view.findViewById<EditText>(R.id.etTripA)
        val tripB = view.findViewById<EditText>(R.id.etTripB)
        val oilKm = view.findViewById<EditText>(R.id.etOilKm)
        val rearBrakeKm = view.findViewById<EditText>(R.id.etRearBrakeKm)
        val frontBrakeKm = view.findViewById<EditText>(R.id.etFrontBrakeKm)

        // Cargar datos guardados
        totalKm.setText(preferences.getString("totalKm", ""))
        tripA.setText(preferences.getString("tripA", ""))
        tripB.setText(preferences.getString("tripB", ""))
        oilKm.setText(preferences.getString("oilKm", ""))
        rearBrakeKm.setText(preferences.getString("rearBrakeKm", ""))
        frontBrakeKm.setText(preferences.getString("frontBrakeKm", ""))

        // Guardar datos
        view.findViewById<Button>(R.id.btnSaveMotorcycle)
            .setOnClickListener {

                preferences.edit().apply {
                    putString("totalKm", totalKm.text.toString())
                    putString("tripA", tripA.text.toString())
                    putString("tripB", tripB.text.toString())
                    putString("oilKm", oilKm.text.toString())
                    putString("rearBrakeKm", rearBrakeKm.text.toString())
                    putString("frontBrakeKm", frontBrakeKm.text.toString())
                    apply()
                }

                dismiss()
            }

        // Cancelar
        view.findViewById<Button>(R.id.btnCancelMotorcycle)
            .setOnClickListener {
                dismiss()
            }
    }
}
