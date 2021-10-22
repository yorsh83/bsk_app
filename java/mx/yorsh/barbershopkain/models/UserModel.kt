package mx.yorsh.barbershopkain.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("username")
    var username: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("whats")
    var email: String?,
    @SerializedName("pass")
    var pass: String?,
    @SerializedName("visits")
    var visits: Int?
)