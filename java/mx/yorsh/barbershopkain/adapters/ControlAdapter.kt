package mx.yorsh.barbershopkain.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.appointment_control.view.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.activities.ControlActivity
import mx.yorsh.barbershopkain.activities.MainActivity
import mx.yorsh.barbershopkain.models.AppointmentModel
import mx.yorsh.barbershopkain.models.ControlModel

class ControlAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<ControlModel>()

    public fun addItems(data: List<ControlModel>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ControlViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(controlModel: ControlModel, position: Int) {
            itemView.tvNameCtrl.text = controlModel.name
            itemView.tvWhatsCtrl.text = controlModel.whatsapp

            itemView.setOnClickListener {
                val intent = Intent(context, ControlActivity::class.java)
                intent.putExtra(MainActivity.ITEM, controlModel)
                context.startActivity(intent)
            }
        }
    }

    //LLAMA AL DISEÑO
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.appointment_control, parent, false)
        return ControlViewHolder(view)
    }

    //VINCULA EL DISEÑO CON LOS DATOS
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ControlAdapter.ControlViewHolder) {
            holder.bind(list[position], position)
        }
    }

    //CANTIDAD DE ELEMENTOS
    override fun getItemCount(): Int {
        return list.size
    }
}