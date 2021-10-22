package mx.yorsh.barbershopkain.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.SharedPreferenceHelper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            /*val preferences = SharedPreferenceHelper(this)
            val intent = if (preferences.getUsername() == "") {
                Intent(this, MenuActivity::class.java)
            } else {
                Intent(this, MenuActivity::class.java)
            }*/
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}