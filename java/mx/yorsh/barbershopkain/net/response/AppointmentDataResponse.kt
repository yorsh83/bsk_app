package mx.yorsh.barbershopkain.net.response

import com.google.gson.annotations.SerializedName

data class AppointmentDataResponse(
    @SerializedName("idCita")
    var idCita: Int?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("whatsapp")
    var whatsapp: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("hour")
    var hour: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("idUserC")
    var idUserC: Int?
)