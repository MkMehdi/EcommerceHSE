package ma.demo.ecommerceappdemo.ui.cotegroytree.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_tree_custom.view.*
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.ui.cotegroytree.adapter.CategoryTreeAdapter.CategoryTreeViewHolder
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * XPI
 * elmehdi.mellouk@xpi.com
 */
class CategoryTreeAdapter(
    private val items: ArrayList<Category>?,
    private val listener: Listener
) : RecyclerView.Adapter<CategoryTreeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryTreeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_tree_custom, parent, false)
        return CategoryTreeViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: CategoryTreeViewHolder,
        position: Int
    ) {
        val item = items!![position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class CategoryTreeViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {

        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        fun set(item: Category?) {

            itemView.constraintLayout.setBackgroundColor(color)

            itemView.text.text = item?.displayName

            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }


    interface Listener {
        fun onItemClick(category:Category)
    }

}