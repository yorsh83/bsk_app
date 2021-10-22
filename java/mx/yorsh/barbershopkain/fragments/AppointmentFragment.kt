package mx.yorsh.barbershopkain.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_appointment.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.SharedPreferenceHelper
import mx.yorsh.barbershopkain.activities.MenuActivity
import mx.yorsh.barbershopkain.models.AppointmentModel
import mx.yorsh.barbershopkain.net.ApiServices
import mx.yorsh.barbershopkain.net.RetrofitConfiguration
import mx.yorsh.barbershopkain.net.response.AppointmentDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AppointmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppointmentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    private val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

    private val dateVisual = SimpleDateFormat("dd MMM, YYYY", Locale.ENGLISH)
    private val timeVisual = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val now: Calendar = Calendar.getInstance()
        val selectedDate = Calendar.getInstance()
        val selectedTime: Calendar = Calendar.getInstance()

        tietDateA.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, day)
                    val x = dateVisual.format(selectedDate.time)
                    //Toast.makeText(requireContext(), x, Toast.LENGTH_SHORT).show()
                    tietDateA.setText(x)
                },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH),
            )
            datePicker.show()
            datePicker.datePicker.minDate = now.timeInMillis
            //showDatePickerDialog();
        }

        tietHourA.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set((Calendar.MINUTE), minute)
                    val y = timeVisual.format(selectedTime.time)
                    tietHourA.setText(y)
                }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePicker.show()
        }

        btnAppointmentA.setOnClickListener {
            val preferences = SharedPreferenceHelper(requireContext())
            val username = preferences.getUsername()
            val idUserC = preferences.getIdu()
            val nameA = tietNameA.text.toString()
            val whatsA = tietWhatsappA.text.toString()
            val dateA = dateFormat.format(selectedDate.time)
            val hourA = timeFormat.format(selectedTime.time)
            val descA = ettmlDescA.text.toString()

            val am = AppointmentModel(username, nameA, whatsA, dateA, hourA, descA, "pendiente", idUserC!!)
            val api = RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
            val call = api.addAppointment(am)
            call.enqueue(object : Callback<AppointmentDataResponse> {
                override fun onResponse(
                    call: Call<AppointmentDataResponse>,
                    response: Response<AppointmentDataResponse>
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Se agendó su cita con éxito",
                        Toast.LENGTH_SHORT
                    ).show()
                    val result = response.body()
                    if (result != null) {
                        val intent = Intent(requireContext(), MenuActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                        /*parentFragmentManager.findFragmentById(R.id.signUpFragment)
                            ?.let { it1 ->
                                childFragmentManager.beginTransaction()
                                    .remove(it1).commit()
                            };*/
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No fue posible agendar la cita",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<AppointmentDataResponse>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "No fue posible agendar su cita",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
    }

    /*
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        //val mes = getMonthName(month)
        tietDateA.setText("$day/$month/$year")
    }

    private fun getMonthName(monthOfYear: Int): String? {
        val c = Calendar.getInstance()
        c[Calendar.MONTH] = monthOfYear
        return SimpleDateFormat("M").format(c.time)
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AppointmentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AppointmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}