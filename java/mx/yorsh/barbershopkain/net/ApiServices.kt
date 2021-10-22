package mx.yorsh.barbershopkain.net

import mx.yorsh.barbershopkain.models.AppointmentModel
import mx.yorsh.barbershopkain.models.UserModel
import mx.yorsh.barbershopkain.models.VisitModel
import mx.yorsh.barbershopkain.net.response.AppointmentDataResponse
import mx.yorsh.barbershopkain.net.response.ServiceDataResponse
import mx.yorsh.barbershopkain.net.response.UserDataResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("servicios/listado")
    fun getTypeService(): Call<List<ServiceDataResponse>>

    @GET("citas/listado")
    fun getAppointments(): Call<List<AppointmentDataResponse>>

    @POST("usuarios/agregar")
    fun addUser(@Body userModel: UserModel): Call<UserDataResponse>

    @POST("citas/agregar")
    fun addAppointment(@Body appointmentModel: AppointmentModel): Call<AppointmentDataResponse>

    @POST("usuarios/validacion")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserDataResponse>

    @POST("usuarios/val_username")
    @FormUrlEncoded
    fun getU(
        @Field("username") username: String,
    ): Call<Int>

    @PUT("usuarios/actualizar/{id}")
    fun addVisit(@Body visitModel: VisitModel, @Path("id") id: Int): Call<Int>

    @GET("usuarios/visitas/{id}")
    fun getVisits(@Path("id") id: Int): Call<UserDataResponse>

}