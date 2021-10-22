package mx.yorsh.barbershopkain.models

import android.util.Log
import mx.yorsh.barbershopkain.net.response.ServiceDataResponse

object ServicesModelBuilder {

    private fun convert(data: ServiceDataResponse): ServiceModel {
        return ServiceModel(data.id, data.title, data.image, data.description, data.price)
    }

    fun convertResponse(data: List<ServiceDataResponse>): List<ServiceModel> {
        val result = ArrayList<ServiceModel>()
        for (item in data) {
            result.add(convert(item))
        }
        return result
    }
}