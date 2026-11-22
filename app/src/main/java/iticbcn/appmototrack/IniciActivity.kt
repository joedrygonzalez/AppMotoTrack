package iticbcn.appmototrack

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class IniciActivity : AppCompatActivity() {
    private lateinit var btnSign: Button
    private lateinit var btnLogin: Button
    private var dadesCarregades = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            !dadesCarregades
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inici)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnSign = findViewById(R.id.btn_sign)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun initListeners() {
        btnLogin.setOnClickListener {
            navigateToResult(LoginActivity::class.java)
        }
        btnSign.setOnClickListener {
            navigateToResult(SignActivity::class.java)
        }
    }

    private fun navigateToResult(pantalla: Class<*>) {
        val intent = Intent(this, pantalla)
        startActivity(intent)
    }
}