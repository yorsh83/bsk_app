package mx.yorsh.barbershopkain.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_control.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.SharedPreferenceHelper
import mx.yorsh.barbershopkain.fragments.ControlFragment
import mx.yorsh.barbershopkain.models.ControlModel
import mx.yorsh.barbershopkain.models.VisitModel
import mx.yorsh.barbershopkain.net.ApiServices
import mx.yorsh.barbershopkain.net.RetrofitConfiguration
import mx.yorsh.barbershopkain.net.response.VisitDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ControlActivity : AppCompatActivity() {

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    private val dateFormatParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
    private val timeFormatParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)

    companion object {
        const val ITEM = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val preference = SharedPreferenceHelper(this)
        val item = intent.getParcelableExtra<ControlModel>(ITEM)
        var idC = item?.idCita
        var idUserC = item?.idUserC
        tvUsernameView.text = item?.username
        tvNameView.text = item?.name
        tvWhatsView.text = item?.whatsapp
        var dateParse = dateFormatParse.parse(item?.date)
        var timeParse = timeFormatParse.parse(item?.hour)
        tvDateView.text = dateFormat.format(dateParse)
        tvHourView.text = timeFormat.format(timeParse)
        tvDetailView.text = item?.description

        if (tvUsernameView.text.isEmpty()) {
            btnAddVisitView.isEnabled = false
        }

        //AGREGA REGISTRO DE VISITA
        btnAddVisitView.setOnClickListener {
            val v = preference.getNumVisit()
            val username = item?.username
            val vm = VisitModel(idC, username, v)
            val apiService =
                RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
            val call = apiService.addVisit(vm, idUserC!!)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                ) {
                    val result = response.body()
                    //Log.d("resultado", result.toString())
                    if (result == 1) {
                        Toast.makeText(
                            applicationContext,
                            "VISITA REGISTRADA",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(applicationContext, ControlFragment::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "NO ES POSIBLE REALIZAR EL REGISTRO",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "SERVICIO NO DISPONIBLE",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}