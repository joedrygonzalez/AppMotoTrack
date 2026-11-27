package iticbcn.appmototrack.models

data class Motorcycle(
    val id: String,
    val name: String,
    val type: String,
    val imageUri: String? = null,
    val totalKm: String = "",
    val tripA: String = "",
    val tripB: String = "",
    val oilKm: String = "",
    val rearBrakeKm: String = "",
    val frontBrakeKm: String = ""
)
