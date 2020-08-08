package ma.demo.ecommerceappdemo.ui.products

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
import kotlinx.android.synthetic.main.products_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.model.Product
import ma.demo.ecommerceappdemo.ui.cotegroytree.adapter.CategoryTreeAdapter
import ma.demo.ecommerceappdemo.ui.products.adapter.ProductsAdapter
import org.json.JSONObject

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ProductsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.idCategory = arguments?.getString("idCategory")!!

        Log.d("tag product","id category == ${viewModel.idCategory}")

        loadProducts()
    }


    private fun loadProducts(){
        startProgressView()

        viewModel.ioScope.launch {

            viewModel.productList = AppECommerce().getHse24Api().getProducts(viewModel.idCategory).productResults

            withContext(Dispatchers.Main) {
                buildProductList()

                finishProgressView()
            }
        }
    }



    private fun buildProductList(){
        productRecycler.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter =
                ProductsAdapter(viewModel.idCategory,
                    viewModel.productList, object : ProductsAdapter.Listener {
                    override fun onItemClick(product: Product) {
                        Log.d("tag","clicked::${product.sku}")

                        val bundle = Bundle()
                        bundle.putString("idProduct",product.sku)
                        view?.findNavController()?.navigate(R.id.action_productsFragment_to_detailProductFragment,
                            bundle)

                    }
                })
        }
    }


    private fun startProgressView(){
        progressView.visibility = View.VISIBLE
        productRecycler.visibility = View.GONE
    }

    private fun finishProgressView(){
        progressView.visibility = View.GONE
        productRecycler.visibility = View.VISIBLE
    }
}