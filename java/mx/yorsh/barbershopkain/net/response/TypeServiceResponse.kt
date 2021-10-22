package mx.yorsh.barbershopkain.net.response

import com.google.gson.annotations.SerializedName

data class TypeServiceResponse(
    @SerializedName("data")
    val data: ArrayList<ServiceDataResponse>,
    @SerializedName("estado")
    val status: Boolean,
    @SerializedName("mensaje")
    val message: String
)

data class ServiceDataResponse(
    @SerializedName("idService")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("urlImage")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: String
)