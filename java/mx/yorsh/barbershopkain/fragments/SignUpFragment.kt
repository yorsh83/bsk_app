package mx.yorsh.barbershopkain.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.activities.MenuActivity
import mx.yorsh.barbershopkain.models.UserModel
import mx.yorsh.barbershopkain.net.ApiServices
import mx.yorsh.barbershopkain.net.RetrofitConfiguration
import mx.yorsh.barbershopkain.net.response.UserDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener {
            val username = tietUsernameSU.text.toString()
            val name = tietNameSU.text.toString()
            val gender = spGenderSU.selectedItem.toString()
            val whats = tietWhatsSU.text.toString()
            val pass = tietPassSU.text.toString()
            val passRep = tietPassRepSU.text.toString()

            if (username == "") {
                tilUsernameSU.error = getString(R.string.error_username)
                return@setOnClickListener
            } else {
                tilUsernameSU.error = null
            }
            if (name == "") {
                tilNameSU.error = getString(R.string.error_name)
                return@setOnClickListener
            } else {
                tilNameSU.error = null
            }
            if (whats == "") {
                tilWhatsSU.error = getString(R.string.error_whats)
                return@setOnClickListener
            } else {
                tilWhatsSU.error = null
            }
            if (pass == "") {
                tilPassSU.error = getString(R.string.error_password)
                return@setOnClickListener
            } else {
                tilPassSU.error = null
            }
            if (passRep == "") {
                tilPassRepSU.error = getString(R.string.error_password)
                return@setOnClickListener
            } else {
                tilPassRepSU.error = null
            }

            //SERVICO VALIDA USERNAME
            val api = RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
            val call = api.getU(username)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    val res = response.body()
                    //Log.d("valor", res.toString())
                    if (res == 1) {
                        Toast.makeText(
                            requireContext(),
                            "EL USUARIO YA EXISTE, SELECCIONE OTRO USUARIO",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        //SERVICIO VALIDA PASS PARA CREAR
                        if (pass == passRep) {
                            val um = UserModel(username, name, gender, whats, pass, 0)
                            val api = RetrofitConfiguration.getConfiguration()
                                .create(ApiServices::class.java)
                            val call = api.addUser(um)
                            call.enqueue(object : Callback<UserDataResponse> {
                                override fun onResponse(
                                    call: Call<UserDataResponse>,
                                    response: Response<UserDataResponse>
                                ) {
                                    Toast.makeText(
                                        requireContext(),
                                        "El usuario fue creado con exito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val result = response.body()
                                    if (result != null) {
                                        val intent =
                                            Intent(requireContext(), MenuActivity::class.java)
                                        startActivity(intent)
                                        requireActivity().finish()
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "No fue posible crear el usuario",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                                    Toast.makeText(
                                        requireContext(),
                                        "No fue posible crear el usuario",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Las contraseñas no coinciden",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "NO ES POSIBLE VALIDAR EL USUARIO",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}