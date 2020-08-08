package ma.demo.ecommerceappdemo.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_tree_custom.view.*
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.model.Category

/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */
class CategoriesAdapter(
    private val items: List<Category>?,
    private val listener: Listener
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_custom, parent, false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val item = items!![position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class CategoryViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        fun set(item: Category?) {
            itemView.text.text = item?.displayName

            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }


    interface Listener {
        fun onItemClick(category:Category)
    }

}