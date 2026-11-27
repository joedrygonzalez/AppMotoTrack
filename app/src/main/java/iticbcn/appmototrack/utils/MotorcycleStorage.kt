package iticbcn.appmototrack.utils

import android.content.Context
import iticbcn.appmototrack.models.Motorcycle
import org.json.JSONArray
import org.json.JSONObject

object MotorcycleStorage {

    private const val PREFS_NAME = "MotorcyclesData"
    private const val KEY_MOTORCYCLES = "motorcycles"

    // Obtener todas las moto
    fun getAll(context: Context): MutableList<Motorcycle> {

        val preferences = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        val json = preferences.getString(KEY_MOTORCYCLES, "[]")
        val array = JSONArray(json)

        val motorcycles = mutableListOf<Motorcycle>()

        for (i in 0 until array.length()) {

            val item = array.getJSONObject(i)

            motorcycles.add(
                Motorcycle(
                    id = item.getString("id"),
                    name = item.getString("name"),
                    type = item.getString("type"),
                    imageUri = if (item.isNull("imageUri")) {
                        null
                    } else {
                        item.getString("imageUri")
                    },
                    totalKm = item.optString("totalKm", ""),
                    tripA = item.optString("tripA", ""),
                    tripB = item.optString("tripB", ""),
                    oilKm = item.optString("oilKm", ""),
                    rearBrakeKm = item.optString("rearBrakeKm", ""),
                    frontBrakeKm = item.optString("frontBrakeKm", "")
                )
            )
        }

        return motorcycles
    }

    // Guardar todas las moto
    fun saveAll(context: Context, motorcycles: List<Motorcycle>) {

        val array = JSONArray()

        for (motorcycle in motorcycles) {

            val item = JSONObject()

            item.put("id", motorcycle.id)
            item.put("name", motorcycle.name)
            item.put("type", motorcycle.type)
            item.put("imageUri", motorcycle.imageUri ?: JSONObject.NULL)
            item.put("totalKm", motorcycle.totalKm)
            item.put("tripA", motorcycle.tripA)
            item.put("tripB", motorcycle.tripB)
            item.put("oilKm", motorcycle.oilKm)
            item.put("rearBrakeKm", motorcycle.rearBrakeKm)
            item.put("frontBrakeKm", motorcycle.frontBrakeKm)

            array.put(item)
        }

        context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        ).edit().putString(KEY_MOTORCYCLES, array.toString()).apply()
    }

    // Añadir moto
    fun add(context: Context, motorcycle: Motorcycle) {
        val motorcycles = getAll(context)
        motorcycles.add(motorcycle)
        saveAll(context, motorcycles)
    }

    // Modificar moto
    fun update(context: Context, motorcycle: Motorcycle) {
        val motorcycles = getAll(context)

        val index = motorcycles.indexOfFirst {
            it.id == motorcycle.id
        }

        if (index != -1) {
            motorcycles[index] = motorcycle
            saveAll(context, motorcycles)
        }
    }

    // Eliminar moto
    fun delete(context: Context, id: String) {
        val motorcycles = getAll(context)

        motorcycles.removeAll {
            it.id == id
        }

        saveAll(context, motorcycles)
    }
}
