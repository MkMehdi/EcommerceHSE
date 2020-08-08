package ma.demo.ecommerceappdemo.ui.basket.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_basket_custom.view.*
import kotlinx.android.synthetic.main.product_custom.view.*
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.db.entity.ProductEntity
import ma.demo.ecommerceappdemo.model.Product

/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */
class ProductsBasketAdapter(
    private val items: List<ProductEntity>?,
    private val listener: Listener
) : RecyclerView.Adapter<ProductsBasketAdapter.ProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_basket_custom, parent, false)
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
        fun set(item: ProductEntity?) {
            itemView.productBasketTitle.text = item?.title
            itemView.productBasketPrice.text = "${item?.price} ${item?.currency}"

            itemView.productBasketDelete.setOnClickListener { listener.onRemoveClick(item!!) }

            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }

    interface Listener {
        fun onRemoveClick(product: ProductEntity)
        fun onItemClick(product:ProductEntity)
    }

}