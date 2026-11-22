package iticbcn.appmototrack.models

/**
 * Data Class per als recordatoris.
 */
data class Reminder(
    val id: Int,
    val title: String,
    val date: String = "",
    val category: String = "General"
)
