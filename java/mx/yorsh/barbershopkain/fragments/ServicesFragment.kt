package mx.yorsh.barbershopkain.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_services.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.adapters.ServiceAdapter
import mx.yorsh.barbershopkain.models.ServiceModel
import mx.yorsh.barbershopkain.models.ServicesModelBuilder
import mx.yorsh.barbershopkain.net.ApiServices
import mx.yorsh.barbershopkain.net.RetrofitConfiguration
import mx.yorsh.barbershopkain.net.response.ServiceDataResponse
import mx.yorsh.barbershopkain.net.response.TypeServiceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ServicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServicesFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ServiceAdapter(requireContext())
        rvService.layoutManager = GridLayoutManager(requireContext(), 1)
        rvService.adapter = adapter

        //val list = ArrayList<ServiceModel>()

        val apiServices = RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
        val call = apiServices.getTypeService()
        call.enqueue(object : Callback<List<ServiceDataResponse>> {
            override fun onResponse(
                call: Call<List<ServiceDataResponse>>,
                response: Response<List<ServiceDataResponse>>
            ) {
                var listResp = response.body() as ArrayList<ServiceDataResponse>
                adapter.addItems(ServicesModelBuilder.convertResponse(listResp))
            }

            override fun onFailure(call: Call<List<ServiceDataResponse>>, t: Throwable) {
                Log.d("log-api-error", t.message.toString())
            }

        })
        /*val item1 = ServiceModel(
            1,
            "Cortes",
            "https://i.pinimg.com/originals/58/95/56/5895569f805b08685af3f33caf6c137f.jpg",
            "Tomate un espacio en tu vida y haremos magia con tu pelo, te lo peinamos a tu estilo... para que despues te despeinen.",
            "$80.00"
        )
        val item2 = ServiceModel(
            2,
            "Mascarillas",
            "https://i2.wp.com/thehappening.com/wp-content/uploads/2017/04/mascarilla-hombres.jpg?fit=1024%2C694&ssl=1",
            "Acercate con nosotros y te eliminamos esos puntos negros de tu bello rostro para lucir perfecto a esa cita.",
            "$100.00"
        )
        val item3 = ServiceModel(
            3,
            "Cejas",
            "https://modaellos.com/wp-content/uploads/2012/07/depilar-el-entrecejo-600x400.jpg",
            "Deseas lucir un rostro mas limpio?, te limpiamos tu ceja a corde a tu estilo manteniendo tu forma original.",
            "$50.00"
        )

        list.add(item1)
        list.add(item2)
        list.add(item3)

        adapter.addItems(list)*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ServicesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ServicesFragment()
    }
}