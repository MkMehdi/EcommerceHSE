package ma.demo.ecommerceappdemo.ui.detailproduct

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.detail_product_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.demo.ecommerceappdemo.AppECommerce
import ma.demo.ecommerceappdemo.R
import ma.demo.ecommerceappdemo.db.entity.ProductEntity


class DetailProductFragment : Fragment() {

    companion object {
        fun newInstance() = DetailProductFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(DetailProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.idProduct = arguments?.getString("idProduct")!!

        loadProductDetail()

        addToCardBtn.setOnClickListener {
            addProduct()
        }
    }


    private fun loadProductDetail(){
        startProgressView()

        viewModel.ioScope.launch {
            viewModel.product =  AppECommerce().getHse24Api().getDetailProduct(viewModel.idProduct)

            withContext(Dispatchers.Main) {
                titleProductText.text = viewModel.product?.title

                descProductText.movementMethod = ScrollingMovementMethod()
                descProductText.text = HtmlCompat.fromHtml(viewModel.product?.longDescription!!,HtmlCompat.FROM_HTML_MODE_LEGACY)

                priceProductText.text = "${viewModel.product?.productPrice?.price} ${viewModel.product?.productPrice?.currency}"

                finishProgressView()
            }
        }
    }

    private fun addProduct(){
        viewModel.ioScope.launch {

            val result = AppECommerce().getRepository()?.addProduct(ProductEntity(
                sku = viewModel.product?.sku!!,
                title = viewModel.product?.title!!,
                price = viewModel.product?.productPrice?.price!!,
                currency = viewModel.product?.productPrice?.currency!!
            ))

            if(result != null)
                if(result > -1)
            withContext(Dispatchers.Main){
                Snackbar.make(productDetailLayout, "Successfully added", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    private fun startProgressView(){
        progressView.visibility = View.VISIBLE
        productDetailLayout.visibility = View.GONE
    }

    private fun finishProgressView(){
        progressView.visibility = View.GONE
        productDetailLayout.visibility = View.VISIBLE
    }

}