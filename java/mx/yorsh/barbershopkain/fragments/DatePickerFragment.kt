package mx.yorsh.barbershopkain.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import mx.yorsh.barbershopkain.R
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment(val listener: (year: Int, month: Int, day: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val today = Calendar.getInstance()
        val year: Int = today.get(Calendar.YEAR)
        val mont: Int = today.get(Calendar.MONTH)
        val day: Int = today.get(Calendar.DAY_OF_MONTH)

        val picker =
            DatePickerDialog(requireContext(), R.style.datePickerTheme, this, year, mont, day)
        picker.datePicker.minDate = today.timeInMillis
        return picker
    }
}

/*println(SimpleDateFormat().format(today.time))
println(SimpleDateFormat("MMMM, d, Y").format(today.time)) //nombre mes dia año
println(SimpleDateFormat("M / d / Y").format(today.time)) //mes / dia / año
println(SimpleDateFormat("EEE").format(today.time)) //abrev dia sem
println(SimpleDateFormat("EEEE").format(today.time)) //nombre de dia semana

println(SimpleDateFormat("H").format(today.time))//reloj 24
println(SimpleDateFormat("h").format(today.time))//reloj 12
println(SimpleDateFormat("a").format(today.time))//am o pm
println(SimpleDateFormat("H:m:s:SSS").format(today.time))//hr, min, seg*/