package mx.yorsh.barbershopkain.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_service.view.*
import mx.yorsh.barbershopkain.R
import mx.yorsh.barbershopkain.fragments.ServicesFragment
import mx.yorsh.barbershopkain.models.ServiceModel

class MainActivity : AppCompatActivity() {
    companion object {
        const val ITEM = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*supportFragmentManager.beginTransaction()
            .replace(R.id.container, ServicesFragment.newInstance()).commit()*/

        val item = intent.getParcelableExtra<ServiceModel>(ITEM)
        title = item?.title
        tvTitleDet.text = item?.title
        tvDetailsDet.text = item?.description
        val url = GlideUrl(
            item?.url,
            LazyHeaders.Builder().addHeader("User-Agent", "xxx").build()
        )
        Glide.with(this).load(url).into(ivImageDet)
        tvPriceDet.text = item?.price
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}