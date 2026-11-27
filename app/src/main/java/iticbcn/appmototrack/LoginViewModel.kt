package iticbcn.appmototrack

// LoginViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iticbcn.appmototrack.models.User

sealed class EstadoLogin {
    data class Exitoso(val usuario: User) : EstadoLogin()
    data class Error(val mensaje: String) : EstadoLogin()
}

class LoginViewModel : ViewModel() {

    private val _estado = MutableLiveData<EstadoLogin>()
    val estado: LiveData<EstadoLogin> = _estado

    fun iniciarSesion(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _estado.value = EstadoLogin.Error("Completa todos los campos")
            return
        }

        val usuario = UsuarioRepository.autenticar(email, password)
        _estado.value = if (usuario != null) {
            EstadoLogin.Exitoso(usuario)
        } else {
            EstadoLogin.Error("Correo o contraseña incorrectos")
        }
    }
}