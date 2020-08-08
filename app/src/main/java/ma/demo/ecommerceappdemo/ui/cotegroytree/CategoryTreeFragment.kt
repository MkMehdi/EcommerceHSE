package ma.demo.ecommerceappdemo.ui.cotegroytree

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.catgeroy_tree_fragment.*
import kotlinx.android.synthetic.main.catgeroy_tree_fragment.progressView
import kotlinx.android.synthetic.main.detail_product_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.api.Hse24Api
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.ui.cotegroytree.adapter.CategoryTreeAdapter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoryTreeFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryTreeFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CategoryTreeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catgeroy_tree_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        loadCategoryTree()
    }

    private fun loadCategoryTree(){
        startProgressView()
        viewModel.ioScope.launch {
            viewModel.categoryTreeList =  AppECommerce().getHse24Api().getCategoryTree().children

            withContext(Dispatchers.Main) {
                buildCategoryTree()
                finishProgressView()
            }
        }
    }

    private fun buildCategoryTree(){

        categoryTreeRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                CategoryTreeAdapter(viewModel.categoryTreeList, object : CategoryTreeAdapter.Listener {
                    override fun onItemClick(category: Category) {
                        Log.d("tag","clicked::${category.categoryId}")

                        val bundle = Bundle()
                        bundle.putParcelableArrayList("categories",category.children)
                        view?.findNavController()?.navigate(R.id.action_categoryTreeFragment_to_categoriesFragment,
                            bundle)

                    }
                })
        }
    }


    private fun startProgressView(){
        progressView.visibility = View.VISIBLE
        categoryTreeRecycler.visibility = View.GONE
    }

    private fun finishProgressView(){
        progressView.visibility = View.GONE
        categoryTreeRecycler.visibility = View.VISIBLE
    }

}