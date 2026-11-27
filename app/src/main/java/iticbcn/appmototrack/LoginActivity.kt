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

    class LoginActivity : AppCompatActivity() {
        private lateinit var btnLogin: Button
        private lateinit var textSign: TextView
        private lateinit var email: EditText
        private lateinit var password: EditText
        private lateinit var btnViewPassword: ImageButton
        private lateinit var textForgottenPass: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_login)
            initComponents()
            initListeners()
        }

        // Eventos de los botones
        private fun initListeners() {
            btnLogin.setOnClickListener {
                navigateToResult(MainActivity::class.java, email.text.toString(), password.text.toString())
            }

            textSign.setOnClickListener {
                navigateToResult(SignActivity::class.java, null, null)
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
        }

        // Inicialización de componentes
        private fun initComponents() {
            btnLogin = findViewById(R.id.btn_login)
            textSign = findViewById(R.id.text_sign)
            email = findViewById(R.id.email)
            password = findViewById(R.id.password)
            btnViewPassword = findViewById(R.id.btnViewPassword)
            textForgottenPass = findViewById(R.id.text_forgottenPass)
        }

        // Navegación
        private fun navigateToResult(pantalla: Class<*>, email: String?, password: String?) {
            val intent = Intent(this, pantalla)
            if (email != null && password != null) {
                intent.putExtra("email", email)
                intent.putExtra("password", password)
            }
            startActivity(intent)
        }
    }