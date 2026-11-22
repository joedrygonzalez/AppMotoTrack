package iticbcn.appmototrack.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Object (Singleton) per a utilitats de dates.
 */
object DateUtils {
    const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"

    fun getCurrentDateFormatted(): String {
        return Date().toFormattedString(DEFAULT_DATE_FORMAT)
    }
}

/**
 * Funció d'extensió per a la classe Date per formatejar dates.
 */
fun Date.toFormattedString(pattern: String = DateUtils.DEFAULT_DATE_FORMAT): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}

/**
 * Funció d'extensió per a la classe String per posar la primera lletra en majúscula.
 */
fun String.toCapitalized(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
