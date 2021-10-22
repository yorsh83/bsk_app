package mx.yorsh.barbershopkain.models

import mx.yorsh.barbershopkain.net.response.AppointmentDataResponse

object ControlModelBuilder {

    private fun convert(data: AppointmentDataResponse): ControlModel {
        return ControlModel(
            data.idCita,
            data.username,
            data.name,
            data.whatsapp,
            data.date,
            data.hour,
            data.description,
            data.status,
            data.idUserC
        )
    }

    fun convertResponse(data: List<AppointmentDataResponse>): List<ControlModel> {
        val result = ArrayList<ControlModel>()
        for (item in data) {
            result.add(convert(item))
        }
        return result
    }
}