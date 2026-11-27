package iticbcn.appmototrack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import iticbcn.appmototrack.models.User

sealed class EstadoSign {
    data class Exitoso(val email: String) : EstadoSign()
    data class Error(val mensaje: String) : EstadoSign()
}

class SignViewModel(application: Application) : AndroidViewModel(application) {

    private val _estado = MutableLiveData<EstadoSign>()
    val estado: LiveData<EstadoSign> = _estado

    fun sign(user: String, email: String, password: String, confirmPassword: String) {
        when (val resultado = validarSign(user, email, password, confirmPassword)) {
            is ResultadoValidacion.Error -> {
                val mensajeError = getApplication<Application>().getString(resultado.resId)
                _estado.value = EstadoSign.Error(mensajeError)
            }

            is ResultadoValidacion.Exitoso -> {
                if (UsuarioRepository.exist(email)) {
                    _estado.value = EstadoSign.Error(context.getString(R.string.ErrEmailExist))
                } else {
                    UsuarioRepository.sign(User(user.trim(), email.trim(), password))
                    _estado.value = EstadoSign.Error(email.trim()) // wait, Exitoso(email)
                    _estado.value = EstadoSign.Exitoso(email.trim())
                }
            }
        }
    }
}
