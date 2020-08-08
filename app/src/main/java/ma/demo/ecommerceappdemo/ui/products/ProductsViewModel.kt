package ma.demo.ecommerceappdemo.ui.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.model.Product

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)

    var idCategory:String = ""
    var productList: ArrayList<Product> = ArrayList()

}