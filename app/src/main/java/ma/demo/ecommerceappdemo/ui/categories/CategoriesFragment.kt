package ma.demo.ecommerceappdemo.ui.categories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.categories_fragment.*
import kotlinx.android.synthetic.main.catgeroy_tree_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.ui.categories.adapter.CategoriesAdapter
import ma.demo.ecommerceappdemo.ui.cotegroytree.adapter.CategoryTreeAdapter

class CategoriesFragment : Fragment() {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.categoryList = arguments?.getParcelableArrayList("categories")!!

        buildCategoryTree()

    }


    private fun buildCategoryTree(){
        Log.d("tag size","size === " + viewModel.categoryList.size)
        categoryRecycler.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter =
                CategoriesAdapter(viewModel.categoryList, object : CategoriesAdapter.Listener {
                    override fun onItemClick(category: Category) {
                        Log.d("tag","clicked::${category.categoryId}")

                        val bundle = Bundle()
                        bundle.putString("idCategory",category.categoryId)
                        view?.findNavController()?.navigate(R.id.action_categoriesFragment_to_productsFragment,
                            bundle)

                    }
                })
        }
    }




}