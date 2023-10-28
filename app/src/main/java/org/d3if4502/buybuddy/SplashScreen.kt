package org.d3if4502.buybuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.d3if4502.buybuddy.Ui.Login.LoginFragment

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        val intent = Intent(this@SplashScreen, LoginFragment::class.java)
        startActivity(intent)
        finish()
    }
}