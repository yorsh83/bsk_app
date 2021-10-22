package mx.yorsh.barbershopkain.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.SharedPreferenceHelper
import mx.yorsh.barbershopkain.activities.MenuActivity
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
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignIn.setOnClickListener {
            val username = tietUsername.text.toString()
            val password = tietPassword.text.toString()

            if (username == "") {
                tilUsername.error = getString(R.string.error_username)
                tietUsername.requestFocus()
                return@setOnClickListener
            } else {
                tilUsername.error = null
            }
            if (password == "") {
                tilPassword.error = getString(R.string.error_password)
                tietPassword.requestFocus()
                return@setOnClickListener
            } else {
                tilPassword.error = null
            }

            val api = RetrofitConfiguration.getConfiguration().create(ApiServices::class.java)
            val call = api.login(username, password)
            call.enqueue(object : Callback<UserDataResponse> {
                override fun onResponse(
                    call: Call<UserDataResponse>,
                    response: Response<UserDataResponse>
                ) {
                    var result = response.body()
                    Log.d("login", result.toString())
                    if (result != null) {
                        val preference = SharedPreferenceHelper(requireContext())
                        preference.saveIdu(result.idUser)
                        preference.saveUsername(username)
                        preference.saveWhats(result.whats)
                        preference.saveNumVisit(result.visits)
                        val intent = Intent(requireContext(), MenuActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                        Toast.makeText(
                            requireContext(),
                            "BUEN DIA " + preference.getUsername(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                    //Log.d("login", t.message.toString())
                    Toast.makeText(
                        requireContext(),
                        "Usuario y/o contraseña invalido",
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
         * @return A new instance of fragment SignInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}