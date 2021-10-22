package mx.yorsh.barbershopkain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppointmentModel(
    //@SerializedName("username")
    var username: String?,
    //@SerializedName("name")
    var name: String?,
    //@SerializedName("whatsapp")
    var whatsapp: String?,
    //@SerializedName("date")
    var date: String?,
    //@SerializedName("hour")
    var hour: String?,
    //@SerializedName("description")
    var description: String?,
    var status: String?,
    var idUserC: Int
) : Parcelable