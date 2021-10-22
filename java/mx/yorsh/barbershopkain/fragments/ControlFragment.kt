package mx.yorsh.barbershopkain.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_control.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.activities.MenuActivity
import mx.yorsh.barbershopkain.adapters.ControlAdapter
import mx.yorsh.barbershopkain.models.ControlModelBuilder
import mx.yorsh.barbershopkain.net.ApiServices
import mx.yorsh.barbershopkain.net.RetrofitConfiguration
import mx.yorsh.barbershopkain.net.response.AppointmentDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ControlFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ControlAdapter(requireContext())
        rvControl.layoutManager = GridLayoutManager(requireContext(), 1)
        rvControl.adapter = adapter

        val apiServices = RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
        val call = apiServices.getAppointments()
        call.enqueue(object : Callback<List<AppointmentDataResponse>> {
            override fun onResponse(
                call: Call<List<AppointmentDataResponse>>,
                response: Response<List<AppointmentDataResponse>>
            ) {
                if (response.body() != null) {
                    var listAppoint = response.body() as ArrayList<AppointmentDataResponse>
                    Log.d("app", response.body().toString())
                    adapter.addItems(ControlModelBuilder.convertResponse(listAppoint))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No hay citas registradas",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(requireContext(), MenuActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }

            }

            override fun onFailure(call: Call<List<AppointmentDataResponse>>, t: Throwable) {
                //Log.d("log-api-error", t.message.toString())
                Toast.makeText(
                    requireContext(),
                    "No hay servicio",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ControlFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ControlFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}