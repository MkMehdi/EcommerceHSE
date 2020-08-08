package ma.demo.ecommerceappdemo.ui.basket

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.basekt_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.db.entity.ProductEntity
import ma.demo.ecommerceappdemo.ui.basket.adapter.ProductsBasketAdapter

class BasketFragment : Fragment() {

    companion object {
        fun newInstance() = BasketFragment()
    }

    private val viewModel: BasketViewModel by lazy {
        ViewModelProviders.of(this).get(BasketViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.basekt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadSavedProducts()
    }

    private fun loadSavedProducts() {
        startProgressView()

        viewModel.ioScope.launch {
            viewModel.productList = AppECommerce().getRepository()?.getProducts()

            Log.d("taggg", "list >> ${viewModel.productList?.size}")

            withContext(Dispatchers.Main) {
                buildProductBasketList()

                finishProgressView()
            }
        }
    }

    private fun deleteProduct(product: ProductEntity) {
        viewModel.ioScope.launch {
            val result = AppECommerce().getRepository()?.deleteProduct(product)

            if (result != null)
                if (result > -1) {
                    withContext(Dispatchers.Main) {
                        loadSavedProducts()

                        Snackbar.make(layoutRoot, "Successfully removed", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    private fun buildProductBasketList() {
        productBasketRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                ProductsBasketAdapter(
                    viewModel.productList, object : ProductsBasketAdapter.Listener {
                        override fun onRemoveClick(product: ProductEntity) {
                            deleteProduct(product)
                        }

                        override fun onItemClick(product: ProductEntity) {
                            Log.d("tag", "clicked::${product.sku}")

                            val bundle = Bundle()
                            bundle.putString("idProduct", product.sku)
                            view?.findNavController()?.navigate(
                                R.id.action_basketFragment_to_detailProductFragment,
                                bundle
                            )

                        }
                    })
        }
    }

    private fun startProgressView(){
        progressView.visibility = View.VISIBLE
        layoutRoot.visibility = View.GONE
    }

    private fun finishProgressView(){
        progressView.visibility = View.GONE
        layoutRoot.visibility = View.VISIBLE
    }

}