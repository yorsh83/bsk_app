package mx.yorsh.barbershopkain.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuCompat
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.SharedPreferenceHelper

class MenuActivity : AppCompatActivity() {

    companion object {
        const val YADMINH = "yorsh"
        const val AADMINL = "anel"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var preferences: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        preferences = SharedPreferenceHelper(this)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            preferences.delete()
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            Toast.makeText(this, "SESION CERRADA", Toast.LENGTH_SHORT).show()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //VALIDA USUARIO
        navView.menu.getItem(3).isVisible =
            preferences.getUsername() == YADMINH || preferences.getUsername() == AADMINL
        if (preferences.getUsername() != "") {
            navView.menu.getItem(5).isEnabled = false
        } else {
            fab.isEnabled = false
            navView.menu.getItem(6).isEnabled = false
        }
        //Log.d("TEST", "${preferences.getUsername()}")
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.servicesFragment,
                R.id.appointmentFragment,
                R.id.infoFragment,
                R.id.controlFragment,
                R.id.signUpFragment,
                R.id.signInFragment,
                R.id.settingsFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}