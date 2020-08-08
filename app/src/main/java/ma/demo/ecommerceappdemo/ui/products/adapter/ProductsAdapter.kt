package ma.demo.ecommerceappdemo.ui.products.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_custom.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.api.Hse24Api
import ma.demo.ecommerceappdemo.model.Product

/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */
class ProductsAdapter(
    private val idCategory:String,
    private val items: List<Product>?,
    private val listener: Listener
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_custom, parent, false)
        return ProductsViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ProductsViewHolder,
        position: Int
    ) {
        val item = items!![position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ProductsViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @SuppressLint("SetTextI18n")
        fun set(item: Product?) {
            itemView.textNameProduct.text = item?.nameShort
            itemView.textPriceProduct.text = "${item?.productPrice?.price} ${item?.productPrice?.currency}"

            /*CoroutineScope(Dispatchers.IO).launch {
                val json =  AppECommerce().getHse24Api().getJsonProducts(idCategory).asJsonObject

                Log.d("tag json","json >>> ${json.getAsJsonArray("productResults")[0].asJsonObject.get("sku").asString}")


                json.getAsJsonArray("productResults").forEach {
                    if((it.asJsonObject).get("sku").asString == item?.sku) {
                        withContext(Dispatchers.Main) {
                            Glide.with(itemView.context).load(
                                "${Hse24Api.BASE_URL_IMAGE}${it.asJsonObject.get("imageUris").asString.split(",")[0]}"
                            ).into(itemView.productImage)
                        }
                    }

               //     Log.d("tag image","image >>> ${it.asJsonObject.get("imageUris").asString.split(",")}")

                }



            }*/

            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }


    private fun loadImageUri(id:String){

    }

    interface Listener {
        fun onItemClick(product:Product)
    }

}