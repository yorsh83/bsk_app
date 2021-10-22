package mx.yorsh.barbershopkain.models

import com.google.gson.annotations.SerializedName

data class VisitModel(
    @SerializedName("idCita")
    var idCita: Int?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("visits")
    var visits: Int?
)