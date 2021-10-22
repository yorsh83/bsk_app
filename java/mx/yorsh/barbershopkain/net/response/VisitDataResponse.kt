package mx.yorsh.barbershopkain.net.response

import com.google.gson.annotations.SerializedName

data class VisitDataResponse(
    @SerializedName("idUser")
    var idUser: Int?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("visits")
    var visits: Int?
)