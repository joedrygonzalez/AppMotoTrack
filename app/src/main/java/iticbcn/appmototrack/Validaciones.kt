package iticbcn.appmototrack

import androidx.annotation.StringRes
import android.util.Patterns

sealed class ResultadoValidacion {
    object Exitoso : ResultadoValidacion()
    data class Error(@param:StringRes val resId: Int) : ResultadoValidacion()
}

fun validarSign(
    user: String,
    email: String,
    password: String,
    confirmPassword: String
): ResultadoValidacion {
    if (user.isBlank()) return ResultadoValidacion.Error(R.string.ErrUserEmpty)

    if (email.isBlank()) return ResultadoValidacion.Error(R.string.ErrEmailEmpty)
    if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches())
        return ResultadoValidacion.Error(R.string.ErrEmailFormat)

    if (password.length < 8)
        return ResultadoValidacion.Error(R.string.ErrPassLength)
    if (!password.any { it.isUpperCase() })
        return ResultadoValidacion.Error(R.string.ErrPassUpper)
    if (!password.any { it.isLowerCase() })
        return ResultadoValidacion.Error(R.string.ErrPassLower)
    if (!password.any { it.isDigit() })
        return ResultadoValidacion.Error(R.string.ErrPassDigit)
    if (password.all { it.isLetterOrDigit() })
        return ResultadoValidacion.Error(R.string.ErrPassChar)

    if (password != confirmPassword)
        return ResultadoValidacion.Error(R.string.ErrPassMatch)

    return ResultadoValidacion.Exitoso
}
