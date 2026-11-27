package iticbcn.appmototrack

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignActivity : AppCompatActivity() {
    private lateinit var textLogin: TextView
    private lateinit var user: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var btnViewPassword: ImageButton
    private lateinit var btnViewConfirmPassword: ImageButton
    private lateinit var btnSign: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign)
        initComponents()
        initListeners()
    }

    // Eventos de los botones
    private fun initListeners() {
        btnSign.setOnClickListener {

            navigateToResult(MainActivity::class.java, user.text.toString(), password.text.toString(), email.text.toString())
        }

        textLogin.setOnClickListener {
            navigateToResult(LoginActivity::class.java, null, null, null)
        }

        btnViewPassword.setOnClickListener {
            val isVisible = !btnViewPassword.isSelected
            btnViewPassword.isSelected = isVisible

            if (isVisible) {
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            password.setSelection(password.text.length)
        }

        btnViewConfirmPassword.setOnClickListener {
            val isVisible = !btnViewConfirmPassword.isSelected
            btnViewConfirmPassword.isSelected = isVisible

            if (isVisible) {
                confirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                confirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            confirmPassword.setSelection(confirmPassword.text.length)
        }
    }

    // Navegación
    fun navigateToResult(pantalla: Class<*>, user: String?, password: String?, email: String?) {
        val intent = Intent(this, pantalla)
        if (email != null && password != null && user != null) {
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("user", user)
        }
        startActivity(intent)
    }

    // Inicialización de componentes
    private fun initComponents() {
        textLogin = findViewById(R.id.text_login)
        user = findViewById(R.id.user)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        btnViewPassword = findViewById(R.id.btnViewPassword)
        btnViewConfirmPassword = findViewById(R.id.btnViewConfirmPassword)
        btnSign = findViewById(R.id.btn_sign)
    }
}