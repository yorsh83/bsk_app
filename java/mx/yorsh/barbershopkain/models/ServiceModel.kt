package mx.yorsh.barbershopkain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceModel(
    var id: Int,
    var title: String,
    var url: String,
    var description: String,
    var price: String
) : Parcelable