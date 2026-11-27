package iticbcn.appmototrack

import iticbcn.appmototrack.models.User

object UsuarioRepository {
    private val Users = mutableMapOf<String, User>()

    fun exist(email: String): Boolean =
        Users.containsKey(email.trim().lowercase())

    fun sign(user: User) {
        Users[user.email.trim().lowercase()] = user
    }

    // Devuelve el usuario si las credenciales son correctas, o null si no
    fun autenticar(email: String, password: String): User? {
        val user = Users[email.trim().lowercase()] ?: return null
        return if (user.password == password) user else null
    }
}