package mx.yorsh.barbershopkain.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import kotlinx.android.synthetic.main.item_service.view.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.activities.MainActivity
import mx.yorsh.barbershopkain.models.ServiceModel

class ServiceAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<ServiceModel>()

    public fun addItems(data: Collection<ServiceModel>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(serviceModel: ServiceModel, position: Int) {
            itemView.tvTitle.text = serviceModel.title
            val url = GlideUrl(
                serviceModel.url,
                LazyHeaders.Builder().addHeader("User-Agent", "xxx").build()
            )
            Glide.with(context).load(url).into(itemView.ivImage)

            //CLICK EN IMAGEN
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(MainActivity.ITEM, serviceModel)
                context.startActivity(intent)
            }
        }
    }

    //LLAMA AL DISEÑO QUE SE VA A ITERAR
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    //VINCULACION CON LOS DATOS
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ServiceViewHolder) {
            holder.bind(list[position], position)
        }
    }

    //CANTIDAD DE ELEMENTOS QUE VA A TENER LA LISTA
    override fun getItemCount(): Int {
        return list.size
    }
}