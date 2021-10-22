package mx.yorsh.barbershopkain.net.response

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("idUser")
    val idUser: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("whats")
    val whats: String,
    @SerializedName("visits")
    var visits: Int
)